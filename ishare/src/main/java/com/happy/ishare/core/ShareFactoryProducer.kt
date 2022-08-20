package com.happy.ishare.core

import android.content.Context
import com.happy.ishare.ShareSDK
import com.happy.ishare.fakeshare.FactoryShareFake
import com.happy.ishare.link.FactoryShareLink
import com.happy.ishare.qq.FactoryShareQQ
import com.happy.ishare.utils.getAppMetaData
import com.happy.ishare.wechat.FactoryShareWechat
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.tencent.tauth.Tencent

class ShareFactoryProducer(private val context: Context, extFactories: List<AbsFactoryShare>?) {
    private val shareFactories = mutableListOf<AbsFactoryShare>()

    init {
        shareFactories.add(createWechatFactory())
        shareFactories.add(createQQFactory())
        shareFactories.add(createCopyLinkFactory())
        shareFactories.add(createFakeFactory())
        extFactories?.forEach {
            shareFactories.add(it)
        }
    }

    fun getShareFactory(platform: Platform): AbsFactoryShare? {
        return shareFactories.firstOrNull {
            it.platform == platform
        }
    }


    private fun createWechatFactory(): FactoryShareWechat {
        val appId = context.getAppMetaData("WECHAT_APPID")
        val api = WXAPIFactory.createWXAPI(context, appId, false)
        return FactoryShareWechat(api)
    }

    private fun createQQFactory(): FactoryShareQQ {
        val appId = context.getAppMetaData("QQ_APPID")
        val api = Tencent.createInstance(appId, context, ShareSDK.authorities)
        return FactoryShareQQ(api)
    }

    private fun createCopyLinkFactory(): FactoryShareLink {
        return FactoryShareLink(context)
    }

    private fun createFakeFactory(): FactoryShareFake {
        return FactoryShareFake()
    }
}
