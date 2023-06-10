package com.grank.db.demo

import android.app.Application
import com.happy.ishare.ShareSDK
import com.happy.ishare.link.FactoryShareLink
import com.happy.ishare.qq.FactoryShareQQ
import com.happy.ishare.wechat.FactoryShareWechat

class AppDemo : Application() {
    override fun onCreate() {
        super.onCreate()
        ShareSDK.init(
            this,
            BuildConfig.DEBUG,
            listOf(FactoryShareQQ(this), FactoryShareWechat(this), FactoryShareLink(this))
        )
    }
}
