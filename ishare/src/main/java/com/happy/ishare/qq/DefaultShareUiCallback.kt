package com.happy.ishare.qq

import com.happy.ishare.ShareCore
import com.tencent.tauth.IUiListener
import com.tencent.tauth.UiError

class DefaultShareUiCallback(val callback: ShareCore.ShareCallback?=null): IUiListener {
    override fun onComplete(p0: Any?) {
        callback?.onSuccess()
    }

    override fun onError(p0: UiError?) {
        callback?.onFail(p0?.errorCode?: ShareCore.ShareCallback.ERR_UNKNOWN, p0?.errorMessage)
    }

    override fun onCancel() {
        callback?.onFail(ShareCore.ShareCallback.ERR_CANCEL, "canceled by user")
    }

    override fun onWarning(p0: Int) {
    }
}
