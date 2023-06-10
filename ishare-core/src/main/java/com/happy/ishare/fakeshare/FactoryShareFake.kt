package com.happy.ishare.fakeshare

import com.happy.ishare.ShareCore
import com.happy.ishare.core.AbsFactoryShare
import com.happy.ishare.core.Platform
import com.happy.ishare.core.Scene
import com.happy.ishare.core.ShareEntity
import com.happy.ishare.core.ShareParam

class FactoryShareFake: AbsFactoryShare() {
    override fun createShareImp(builder: ShareCore.Builder): ShareCore? {
        return FakeShareImpl(ShareParam())
    }

    override fun convertShareEntity2ShareParam(entity: ShareEntity): ShareParam {
        TODO("Not yet implemented")
    }
    override val platform: Platform
        get() = UnKnown

    override val scenes: List<Scene>
        get() = listOf(FakeScene.UnKnow)
}
