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

package com.happy.ishare.link

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import com.happy.ishare.ShareCore

class LinkShare(
    private val context: Context,
    private val param: LinkShareParam,
) : ShareCore(param) {
    override fun shareInner(callback: ShareCallback?) {
        Log.i(TAG, "copy link share: ${param.link}")
        param.link?.let {
            copyLink(it)
            callback?.onSuccess()
        }
    }

    override fun checkParam(callback: ShareCallback?): Boolean =
        if (!param.link.isNullOrEmpty()) {
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
