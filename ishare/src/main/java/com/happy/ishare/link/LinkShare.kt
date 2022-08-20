package com.happy.ishare.link

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import com.happy.ishare.ShareCore


class LinkShare(
    private val context: Context,
    private val param: LinkShareParam
) : ShareCore(param) {
    override fun shareInner(callback: ShareCallback?) {
        Log.i(TAG, "copy link share: ${param.link}")
        param.link?.let {
            copyLink(it)
            callback?.onSuccess()
        }
    }

    override fun checkParam(callback: ShareCallback?): Boolean =
        if(!param.link.isNullOrEmpty()) {
            true
        } else {
            errorMsg = "has no link to copy"
            false
        }

    private fun copyLink(link: String) {
        val clipboard: ClipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("share", link)
        clipboard.setPrimaryClip(clipData)
    }
}
