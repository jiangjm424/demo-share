package com.happy.ishare.link

import android.content.Context
import com.happy.ishare.ShareCore
import com.happy.ishare.core.AbsFactoryShare
import com.happy.ishare.core.Platform
import com.happy.ishare.core.Scene
import com.happy.ishare.core.ShareEntity
import com.happy.ishare.core.ShareParam

class FactoryShareLink(private val context: Context) : AbsFactoryShare() {
    override fun createShareImp(builder: ShareCore.Builder): ShareCore? {
        return LinkShare(context, convertShareEntity2ShareParam(builder.shareEntity!!))
    }

    override fun convertShareEntity2ShareParam(entity: ShareEntity): LinkShareParam {
        return LinkShareParam().apply {
            link = entity.url
        }
    }

    override val platform: Platform
        get() = CopyLink
    override val scenes: List<Scene>
        get() = listOf(LinkScene.CopyLink)
}
