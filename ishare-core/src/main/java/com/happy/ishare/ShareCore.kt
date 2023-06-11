/*
 * Copyright 2023 The IShare Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.happy.ishare

import com.happy.ishare.core.Platform
import com.happy.ishare.core.Scene
import com.happy.ishare.core.ShareEntity
import com.happy.ishare.core.ShareParam
import com.happy.ishare.fakeshare.FakeScene
import com.happy.ishare.fakeshare.UnKnown

// 分享功能实际构建及生成的类
abstract class ShareCore(para: ShareParam) {
    companion object {
        internal const val TAG = "ShareCore"
    }

    protected var errorMsg: String? = null
    fun share(callback: ShareCallback? = null) {
        if (!checkParam(callback)) {
            callback?.onFail(ShareCallback.ERR_INVALID_PARAM, errorMsg)
            return
        }
        shareInner(callback)
//        callback?.onSuccess() //成功与否的这一次回调交由执行分享的类来处理，因为子类知道实际结果
    }

    protected abstract fun shareInner(callback: ShareCallback? = null)

    /**
     * 每个分享中，实际判定的参数合法与否与指定分享的功能相关，所以这里不实现，由具体类实现
     * @param callback ShareCallback?
     * @return Boolean 当返回false时，将不会执行@{shareInner}函数
     */
    protected abstract fun checkParam(callback: ShareCallback?): Boolean

    interface ShareCallback {
        companion object {
            const val ERR_INVALID_PARAM: Int = -1
            const val ERR_CANCEL: Int = -2
            const val ERR_UNKNOWN: Int = -3
        }

        fun onSuccess()
        fun onFail(errCode: Int, errMsg: String?)
    }

    class Builder(platform: Platform) {
        private var sharePlatform: Platform = UnKnown
        var shareEntity: ShareEntity? = null
            private set

        var shareScene: Scene = FakeScene.UnKnow
            private set

        init {
            sharePlatform = platform
        }

        fun build(): ShareCore? {
            if (ShareSDK.DEBUG && shareEntity == null) {
                error("Assertion failed")
            }
            return ShareSDK.shareFactory()?.getShareFactory(sharePlatform)?.createShareImp(this)
        }

        fun platform(platform: Platform) = apply {
            sharePlatform = platform
            shareEntity = null
        }

        fun scene(scene: Scene) = apply {
            this.shareScene = scene
        }

        fun shareEntity(para: ShareEntity) = apply {
            shareEntity = para
        }
    }
}
