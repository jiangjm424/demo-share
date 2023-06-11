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

package com.happy.ishare.wechat.wxapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.happy.ishare.utils.getAppMetaData
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.tencent.mm.opensdk.openapi.WXAPIFactory

class WXEntryActivity : AppCompatActivity(), IWXAPIEventHandler {

    companion object {
        private const val TAG = "WXEntryActivity"
    }
    private val appId by lazy {
        this.getAppMetaData("WECHAT_APPID")
    }
    private val wxApi by lazy {
        WXAPIFactory.createWXAPI(this, appId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "appId $appId")
        try {
            wxApi.handleIntent(intent, this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        wxApi.handleIntent(intent, this)
    }

    // 微信调用本应用时回调此方法，
    // 注意，自己应用在调用wxApi.sendReq时并不会回调此方法
    override fun onReq(req: BaseReq?) {
        Log.i(TAG, "wx onReq:$req")
        finish()
    }

    // 本应用在调用完WxApi.sendReq后返回时会回调此方法，记得finish本Activity，否则此界面会显示在栈顶
    override fun onResp(resp: BaseResp?) {
        Log.i(TAG, "wx onResp:$resp")
        finish()
    }
}
