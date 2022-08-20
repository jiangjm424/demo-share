package com.happy.ishare.wechat

import android.graphics.Bitmap
import com.happy.ishare.core.Scene
import com.happy.ishare.core.ShareParam
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject

sealed class WechatScene(val scene: Int): Scene() {
    object Session : WechatScene(SendMessageToWX.Req.WXSceneSession)
    object Timeline : WechatScene(SendMessageToWX.Req.WXSceneTimeline)
    object Favorite : WechatScene(SendMessageToWX.Req.WXSceneFavorite)
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
    var videoUrl: String? = null
    var musicUrl: String? = null
    var webPageUrl: String? = null
    var bitmap: Bitmap? = null
    var contentType: WechatContent = WechatContent.WEB
    var mTargetScene:WechatScene = WechatScene.Session
    var miniProgramId: String? = null  //分享小程序原始id.  一般是gh_****
    var miniProgramPath: String? = null     //分享小程序path
    var withShareTicket: Boolean = true
}
