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

package com.happy.ishare

import android.content.Context
import android.util.Log
import com.happy.ishare.core.AbsFactoryShare
import com.happy.ishare.core.Scene
import com.happy.ishare.core.ShareFactoryProducer

// 用于初始和保存一些全局上下文变量的类
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
