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

import com.happy.ishare.ShareSDK
import com.happy.ishare.utils.toByteArray
import com.happy.ishare.utils.toThumbnail
import com.tencent.mm.opensdk.modelmsg.WXImageObject
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject
import com.tencent.mm.opensdk.modelmsg.WXMusicObject
import com.tencent.mm.opensdk.modelmsg.WXTextObject
import com.tencent.mm.opensdk.modelmsg.WXVideoObject
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject
import com.tencent.mm.opensdk.openapi.IWXAPI

internal class WechatShare(api: IWXAPI, param: WechatShareParam) : AbsWechatShare(api, param) {

    override fun message(): WXMediaMessage? {
        return when (param.contentType) {
            WechatContent.WEB -> web()
            WechatContent.IMAGE -> image()
            WechatContent.MUSIC -> music()
            WechatContent.TEXT -> text()
            WechatContent.VIDEO -> video()
            WechatContent.MINIPROGRAM -> miniProgram()
        }
    }

    private fun miniProgram(): WXMediaMessage? {
        val err = mutableListOf<String>()
        if (param.miniProgramId.isNullOrEmpty()) {
            err + "miniProgramId"
        }
        if (param.miniProgramPath.isNullOrEmpty()) {
            err + "miniProgramPath"
        }
        err.takeIf { it.isNotEmpty() }?.let {
            errMsg(it)
            return null
        }
        val miniProgramObj = WXMiniProgramObject()
        miniProgramObj.webpageUrl = param.webPageUrl // 兼容低版本的网页链接

        miniProgramObj.miniprogramType =
            if (ShareSDK.DEBUG) {
                WXMiniProgramObject.MINIPROGRAM_TYPE_TEST
            } else {
                WXMiniProgramObject.MINIPTOGRAM_TYPE_RELEASE // 正式版:0，测试版:1，体验版:2
            }
        miniProgramObj.withShareTicket = param.withShareTicket
        miniProgramObj.userName = param.miniProgramId // "gh_d43f693ca31f" // 小程序原始id
        miniProgramObj.path =
            param.miniProgramPath // "/pages/media" //小程序页面路径；对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"
        val msg = WXMediaMessage(miniProgramObj)
        msg.title = param.title // 小程序消息title
        msg.description = param.description // 小程序消息desc
        msg.thumbData = thumbnail(param) // 小程序消息封面图片，小于128k
        return msg
    }

    override fun transaction(): String {
        return param.contentType.name
    }

    private fun video(): WXMediaMessage? {
        val err = mutableListOf<String>()
        if (param.videoUrl.isNullOrEmpty()) {
            err + "videoUrl"
        }
        err.takeIf { it.isNotEmpty() }?.let {
            errMsg(it)
            return null
        }
        val video = WXVideoObject()
        video.videoUrl = param.videoUrl
        val msg = WXMediaMessage(video)
        msg.mediaTagName = param.mediaTagName
//        "mediaTagName"
        msg.messageAction = param.messageAction
//            "MESSAGE_ACTION_SNS_VIDEO#gameseq=1491995805&GameSvrEntity=87929&RelaySvrEntity=2668626528&playersnum=10"
        msg.title = param.title
        msg.description = param.description
        msg.thumbData = thumbnail(param)
        return msg
    }

    // 分享图片可以通过二进制分享，也可以通过文件路径分享，这里面先实现二进制 分享
    private fun image(): WXMediaMessage? {
        val err = mutableListOf<String>()
        if (param.bitmap == null) {
            err + "bitmap"
        }
        err.takeIf { it.isNotEmpty() }?.let {
            errMsg(it)
            return null
        }
        val bmp = param.bitmap
        val imgObj = WXImageObject(bmp) // 通过二进制
//        val imgObj = WXImageObject()   //通过文件分享
//        imgObj.setImagePath(path)
        val msg = WXMediaMessage()
        msg.mediaObject = imgObj
        msg.thumbData = thumbnail(param)
        return msg
    }

    private fun web(): WXMediaMessage? {
        val err = mutableListOf<String>()
        if (param.webPageUrl.isNullOrEmpty()) {
            err + "webPageUrl"
        }
        err.takeIf { it.isNotEmpty() }?.let {
            errMsg(it)
            return null
        }
        val webpage = WXWebpageObject()
        webpage.webpageUrl = param.webPageUrl
        val msg = WXMediaMessage()
        msg.mediaObject = webpage
        msg.title = param.title
        msg.description = param.description
        msg.thumbData = thumbnail(param)
        return msg
    }

    private fun music(): WXMediaMessage? {
        val err = mutableListOf<String>()
        if (param.musicUrl.isNullOrEmpty()) {
            err + "musicUrl"
        }
        err.takeIf { it.isNotEmpty() }?.let {
            errMsg(it)
            return null
        }
        val music = WXMusicObject()
        music.musicUrl = param.musicUrl
        val msg = WXMediaMessage()
        msg.mediaObject = music
        msg.title = param.title
        msg.description = param.description
        msg.thumbData = thumbnail(param)
        return msg
    }

    private fun text(): WXMediaMessage? {
        val err = mutableListOf<String>()
        if (param.description.isNullOrEmpty()) {
            err + "description"
        }
        err.takeIf { it.isNotEmpty() }?.let {
            errMsg(it)
            return null
        }
        val textObj = WXTextObject()
        textObj.text = param.description
        val msg = WXMediaMessage()
        msg.mediaObject = textObj
        msg.description = param.description
        msg.mediaTagName = param.mediaTagName
        return msg
    }

    private fun errMsg(properties: List<String>) {
        errorMsg = "Has no WXShareParam.${
            properties.joinToString(
                ",",
                "{",
                "}",
            )
        } when share wechat:${param.contentType}"
    }

    private fun thumbnail(param: WechatShareParam): ByteArray? {
        if (param.thumbData != null) return param.thumbData
        val bmp = param.bitmap
        val thumbBmp = bmp?.toThumbnail(THUMB_SIZE, THUMB_SIZE)
        return thumbBmp?.toByteArray(true)
    }
}
