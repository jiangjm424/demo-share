package com.happy.ishare.wechat

import com.happy.ishare.ShareCore
import com.happy.ishare.core.AbsFactoryShare
import com.happy.ishare.core.Platform
import com.tencent.mm.opensdk.openapi.IWXAPI

class FactoryShareWechat(private val wxApi: IWXAPI) : AbsFactoryShare {

    override fun createShareImp(builder: ShareCore.Builder): ShareCore {
        return WechatShare(wxApi, builder.shareParam as WechatShareParam)
    }

    override val platform: Platform
        get() = Platform.WeChat
}
