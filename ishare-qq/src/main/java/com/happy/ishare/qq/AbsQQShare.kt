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

package com.happy.ishare.qq

import android.os.Bundle
import com.happy.ishare.ShareCore
import com.happy.ishare.ShareSDK
import com.tencent.tauth.Tencent

abstract class AbsQQShare(protected val qqApi: Tencent, protected val para: QQShareParam) :
    ShareCore(para) {
    protected var bundle: Bundle = Bundle()
    override fun checkParam(callback: ShareCallback?): Boolean {
        applyParamToBundle()
        return errorMsg?.let {
            if (ShareSDK.DEBUG) {
                throw IllegalArgumentException(it)
            }
            false
        } ?: true
    }

    abstract fun applyParamToBundle()
    protected fun errMsg(err: List<String>) =
        "Has no QQShareParam.${err.joinToString(",", "{", "}")} when share qq:${para.contentType}"
}
