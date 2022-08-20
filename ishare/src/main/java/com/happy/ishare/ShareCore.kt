package com.happy.ishare

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import com.happy.ishare.core.Platform
import com.happy.ishare.core.ShareParam
import java.io.ByteArrayOutputStream

//分享功能实际构建及生成的类
abstract class ShareCore(para: ShareParam) {
    companion object {
        internal const val TAG = "ShareCore"
    }

    protected var errorMsg: String? = null
    fun share(callback: ShareCallback?) {
        if (!checkParam(callback)) {
            callback?.onFail(ShareCallback.ERR_INVALID_PARAM,errorMsg)
            return
        }
        shareInner(callback)
//        callback?.onSuccess() //成功与否的这一次回调交由执行分享的类来处理，因为子类知道实际结果
    }

    protected abstract fun shareInner(callback: ShareCallback? = null)

    /**
     * 每个分享中，实际判定的参数合法与否与指定分享的功能相关，所以这里不实现，由具体类实现
     * @param callback ShareCallback?
     * @return Boolean 当返回false时，将不会执行@{shareInner}函数
     */
    protected abstract fun checkParam(callback: ShareCallback?): Boolean

    interface ShareCallback {
        companion object {
            const val ERR_INVALID_PARAM:Int = -1
            const val ERR_CANCEL:Int = -2
            const val ERR_UNKNOWN:Int = -3
        }
        fun onSuccess()
        fun onFail(errCode:Int,errMsg: String?)
    }

    class Builder {
        private var sharePlatform: Platform = Platform.UnKnown
        internal var shareParam: ShareParam? = null
        fun build(): ShareCore? {
            if (ShareSDK.DEBUG && shareParam == null) {
                error("Assertion failed")
            }
            return ShareSDK.shareFactory()?.getShareFactory(sharePlatform)?.createShareImp(this)
        }

        fun setPlatform(platform: Platform): Builder {
            sharePlatform = platform
            return this
        }

        fun setShareParam(para: ShareParam): Builder {
            shareParam = para
            return this
        }
    }
}

internal fun Bitmap.toThumbnail(destW: Int, destH: Int): Bitmap {
    return Bitmap.createScaledBitmap(this, destW, destH, true)
}

internal fun Bitmap.toByteArray(needRecycle: Boolean = false): ByteArray {
    val output = ByteArrayOutputStream()
    this.compress(CompressFormat.PNG, 100, output)
    if (needRecycle) {
        this.recycle()
    }
    val result = output.toByteArray()
    try {
        output.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return result
}
