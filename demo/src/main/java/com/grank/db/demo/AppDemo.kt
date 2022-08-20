package com.grank.db.demo

import android.app.Application
import com.happy.ishare.ShareSDK

class AppDemo : Application() {
    override fun onCreate() {
        super.onCreate()
        ShareSDK.init(this, null, BuildConfig.DEBUG)
    }
}
