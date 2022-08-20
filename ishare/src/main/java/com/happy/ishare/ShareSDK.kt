package com.happy.ishare

import android.content.Context
import android.util.Log
import com.happy.ishare.core.ShareFactoryProducer

//用于初始和保存一些全局上下文变量的类
object ShareSDK {
    private var shareFactoryProducer: ShareFactoryProducer? = null
    var sdkConfig: Config = Config()
        private set
    internal lateinit var authorities: String
        private set
    internal var DEBUG: Boolean = true
        private set

    fun init(context: Context, config: Config? = null, debug: Boolean = true) {
        Log.i("ShareSdk","init")
        config?.let {
            sdkConfig = it
        }
        DEBUG = debug
        authorities = context.packageName + ".share.fileprovider"
        shareFactoryProducer = ShareFactoryProducer(context, null)
    }

    internal fun shareFactory() = shareFactoryProducer

    //todo: 目前没有使用到，后续看看这个配置类是否需要
    class Config {

    }
}
