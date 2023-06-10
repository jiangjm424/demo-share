package com.happy.ishare.wechat

import android.content.Context
import com.happy.ishare.ShareCore
import com.happy.ishare.core.AbsFactoryShare
import com.happy.ishare.core.Platform
import com.happy.ishare.core.Scene
import com.happy.ishare.core.ShareEntity
import com.happy.ishare.core.ShareParam
import com.happy.ishare.utils.getAppMetaData
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory

class FactoryShareWechat(context: Context) : AbsFactoryShare() {

    private val wxApi = createWechatFactory(context)
    private fun createWechatFactory(context: Context): IWXAPI {
        val appId = context.getAppMetaData("WECHAT_APPID")
        return WXAPIFactory.createWXAPI(context, appId, false)
    }

    override fun convertShareEntity2ShareParam(entity: ShareEntity): WechatShareParam {
        val param = entity.attachInfo as? WechatShareParam
        return param ?: WechatShareParam().apply {
            title = entity.title
            description = entity.description
            webPageUrl = entity.url
            thumbData = entity.thumbData
            mTargetScene = entity.scene as? WechatScene ?: WechatScene.Session
        }
    }

    override fun createShareImp(builder: ShareCore.Builder): ShareCore {
        return WechatShare(wxApi, convertShareEntity2ShareParam(builder.shareEntity!!))
    }

    override val platform: Platform
        get() = WeChat
    override val scenes: List<Scene>
        get() = listOf(WechatScene.Session, WechatScene.Timeline, WechatScene.Favorite)
}
