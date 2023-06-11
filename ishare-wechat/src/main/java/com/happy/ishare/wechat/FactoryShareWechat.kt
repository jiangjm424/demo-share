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

package com.happy.ishare.wechat

import android.content.Context
import com.happy.ishare.ShareCore
import com.happy.ishare.core.AbsFactoryShare
import com.happy.ishare.core.Platform
import com.happy.ishare.core.Scene
import com.happy.ishare.core.ShareEntity
import com.happy.ishare.utils.getAppMetaData
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory

class FactoryShareWechat(context: Context) : AbsFactoryShare() {

    private val wxApi = createWechatFactory(context)
    private fun createWechatFactory(context: Context): IWXAPI {
        val appId = context.getAppMetaData("WECHAT_APPID")
        return WXAPIFactory.createWXAPI(context, appId, false)
    }

    override fun convertShareEntity2ShareParam(scene: Scene?, entity: ShareEntity): WechatShareParam {
        val param = entity.attachInfo as? WechatShareParam
        return param ?: WechatShareParam().apply {
            title = entity.title
            description = entity.description
            webPageUrl = entity.url
            thumbData = entity.thumbData
            mTargetScene = scene as? WechatScene ?: WechatScene.Session
        }
    }

    override fun createShareImp(builder: ShareCore.Builder): ShareCore {
        return WechatShare(wxApi, convertShareEntity2ShareParam(builder.shareScene, builder.shareEntity!!))
    }

    override val platform: Platform
        get() = WeChat
    override val scenes: List<Scene>
        get() = listOf(WechatScene.Session, WechatScene.Timeline, WechatScene.Favorite)
}
