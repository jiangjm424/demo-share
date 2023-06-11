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

import android.graphics.Bitmap
import com.happy.ishare.core.Platform
import com.happy.ishare.core.Scene
import com.happy.ishare.core.ShareParam
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage

sealed class WechatScene(val scene: Int) : Scene {
    object Session : WechatScene(SendMessageToWX.Req.WXSceneSession) {
        override val platform: Platform
            get() = WeChat
    }

    object Timeline : WechatScene(SendMessageToWX.Req.WXSceneTimeline) {
        override val platform: Platform
            get() = WeChat
    }

    object Favorite : WechatScene(SendMessageToWX.Req.WXSceneFavorite) {
        override val platform: Platform
            get() = WeChat
    }
}
sealed class WechatContent(val name: String) {
    object WEB : WechatContent("webpage")
    object TEXT : WechatContent("text")
    object MUSIC : WechatContent("music")
    object VIDEO : WechatContent("video")
    object IMAGE : WechatContent("img")
    object MINIPROGRAM : WechatContent("miniProgram")
}

class WechatShareParam : ShareParam() {
    var mediaObject: WXMediaMessage.IMediaObject? = null
    var videoUrl: String? = null
    var musicUrl: String? = null
    var webPageUrl: String? = null
    var bitmap: Bitmap? = null
    var contentType: WechatContent = WechatContent.WEB
    var mTargetScene: WechatScene = WechatScene.Session
    var miniProgramId: String? = null // 分享小程序原始id.  一般是gh_****
    var miniProgramPath: String? = null // 分享小程序path
    var withShareTicket: Boolean = true
}
