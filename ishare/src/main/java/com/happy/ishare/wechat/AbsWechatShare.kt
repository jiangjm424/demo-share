package com.happy.ishare.wechat

import com.happy.ishare.ShareCore
import com.happy.ishare.ShareSDK
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage
import com.tencent.mm.opensdk.openapi.IWXAPI
import java.lang.IllegalArgumentException

//微信分享的基础类，里面可以作一些微信分享逻辑的公共部分
abstract class AbsWechatShare(private val api: IWXAPI, protected val param: WechatShareParam) :
    ShareCore(param) {
    companion object {
        const val THUMB_SIZE = 150
    }
    private var mWXMediaMessage:WXMediaMessage?=null

    override fun checkParam(callback: ShareCallback?): Boolean {
        mWXMediaMessage = message()
        return errorMsg?.let {
            if (ShareSDK.DEBUG) {
                throw IllegalArgumentException(it)
            }
            false
        } ?: true
    }
    override fun shareInner(callback: ShareCallback?) {
        mWXMediaMessage?.let {
            val req = SendMessageToWX.Req()
            req.transaction = buildTransaction(transaction())
            req.message = it
            req.scene = param.mTargetScene.scene
            api.sendReq(req)
            callback?.onSuccess()
        } ?: callback?.onFail(ShareCallback.ERR_INVALID_PARAM, errorMsg)
    }

    abstract fun message(): WXMediaMessage?
    abstract fun transaction():String
    private fun buildTransaction(type: String?): String? {
        val timestamp = System.currentTimeMillis()
        return "${type}$timestamp"
    }
}
