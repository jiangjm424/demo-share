package com.happy.ishare.qq

import android.os.Bundle
import com.happy.ishare.ShareCore
import com.happy.ishare.ShareSDK
import com.tencent.tauth.Tencent

abstract class AbsQQShare(protected val qqApi: Tencent, protected val para: QQShareParam) :
    ShareCore(para) {
    protected var bundle: Bundle = Bundle()
    override fun checkParam(callback: ShareCallback?): Boolean {
        applyParamToBundle()
        return errorMsg?.let {
            if (ShareSDK.DEBUG) {
                throw IllegalArgumentException(it)
            }
            false
        } ?: true
    }

    abstract fun applyParamToBundle()
    protected fun errMsg(err: List<String>) =
        "Has no QQShareParam.${err.joinToString(",", "{", "}")} when share qq:${para.contentType}"
}
