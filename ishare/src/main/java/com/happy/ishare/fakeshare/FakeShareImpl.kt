package com.happy.ishare.fakeshare

import android.util.Log
import com.happy.ishare.ShareCore
import com.happy.ishare.core.ShareParam

class FakeShareImpl(para: ShareParam) : ShareCore(para) {
    override fun shareInner(callback: ShareCallback?) {
        Log.i(TAG, "fake share")
    }

    override fun checkParam(callback: ShareCallback?): Boolean {
        return true
    }
}
