package com.happy.ishare

import android.content.Context
import android.util.Log
import com.happy.ishare.core.AbsFactoryShare
import com.happy.ishare.core.Scene
import com.happy.ishare.core.ShareFactoryProducer

//用于初始和保存一些全局上下文变量的类
object ShareSDK {
    private var shareFactoryProducer: ShareFactoryProducer? = null
    lateinit var authorities: String
        private set
    var DEBUG: Boolean = true
        private set

    fun init(context: Context, debug: Boolean = true, factoryShare: List<AbsFactoryShare>) {
        Log.i("ShareSdk", "init")
        DEBUG = debug
        authorities = context.packageName + ".share.fileprovider"
        shareFactoryProducer = ShareFactoryProducer(factoryShare)
    }

    fun allSupportShareScene(): List<Scene> {
        return shareFactoryProducer?.supportScenes() ?: emptyList()
    }

    internal fun shareFactory() = shareFactoryProducer
}
