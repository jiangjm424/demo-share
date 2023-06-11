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

import com.happy.ishare.ShareSDK
import com.tencent.connect.share.QQShare
import com.tencent.tauth.Tencent

class QQShareImp(qqApi: Tencent, para: QQShareParam) : AbsQQShare(qqApi, para) {

    override fun applyParamToBundle() {
        when (para.contentType) {
            QQContent.DEFAULT -> {
                normal()
            }
            QQContent.AUDIO -> {
                audio()
            }
            QQContent.MINIPROGRAM -> {
                miniProgram()
            }
            else -> {
                errorMsg = "Not Supported content type:${para.contentType} to share "
            }
        }
    }

    override fun shareInner(callback: ShareCallback?) {
        qqApi.shareToQQ(para.host, bundle, DefaultShareUiCallback(callback))
    }

    private fun miniProgram() {
        val err = mutableListOf<String>()
        if (para.title.isNullOrEmpty()) {
            err + "title"
        }
        if (para.webPageUrl.isNullOrEmpty()) {
            err + "webPageUrl"
        }
        if (para.description.isNullOrEmpty()) {
            err + "description"
        }
        if (para.imageUrl.isNullOrEmpty()) {
            err + "imageUrl"
        }
        if (para.miniProgramId.isNullOrEmpty()) {
            err + "miniProgramId"
        }
        if (para.miniProgramPath.isNullOrEmpty()) {
            err + "miniProgramPath"
        }
        err.takeIf { it.isNotEmpty() }?.let {
            errMsg(it)
            return
        }
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, para.contentType.type)
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, para.title)
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, para.description)
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, para.webPageUrl)
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, para.imageUrl)
        bundle.putString(QQShare.SHARE_TO_QQ_MINI_PROGRAM_APPID, para.miniProgramId)
        bundle.putString(QQShare.SHARE_TO_QQ_MINI_PROGRAM_PATH, para.miniProgramPath)
        val miniProgramType = if (ShareSDK.DEBUG) "1" else "3"
        bundle.putString(QQShare.SHARE_TO_QQ_MINI_PROGRAM_TYPE, miniProgramType)
    }

    private fun normal() {
        var err = mutableListOf<String>()
        if (para.title.isNullOrEmpty()) {
            err + "title"
        }
        if (para.webPageUrl.isNullOrEmpty()) {
            err + " webPageUrl"
        }
        err.takeIf { it.isNotEmpty() }?.let {
            errMsg(it)
            return
        }
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, para.contentType.type)
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, para.title)
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, para.description)
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, para.webPageUrl)
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, para.imageUrl)
    }

    private fun audio() {
        var err = mutableListOf<String>()
        if (para.title.isNullOrEmpty()) {
            err + "title"
        }
        if (para.webPageUrl.isNullOrEmpty()) {
            err + "webPageUrl"
        }
        if (para.audioUrl.isNullOrEmpty()) {
            err + "audioUrl"
        }
        err.takeIf { it.isNotEmpty() }?.let {
            errMsg(it)
            return
        }
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, para.contentType.type)
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, para.title)
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, para.description)
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, para.webPageUrl)
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, para.imageUrl)
        bundle.putString(QQShare.SHARE_TO_QQ_AUDIO_URL, para.audioUrl)
    }
}
