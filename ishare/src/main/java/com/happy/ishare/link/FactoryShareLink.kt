package com.happy.ishare.link

import android.content.Context
import com.happy.ishare.ShareCore
import com.happy.ishare.core.AbsFactoryShare
import com.happy.ishare.core.Platform

class FactoryShareLink(private val context: Context) : AbsFactoryShare {
    override fun createShareImp(builder: ShareCore.Builder): ShareCore? {
        return LinkShare(context, builder.shareParam as LinkShareParam)
    }

    override val platform: Platform
        get() = Platform.CopyLink
}
