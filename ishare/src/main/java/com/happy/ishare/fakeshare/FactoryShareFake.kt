package com.happy.ishare.fakeshare

import com.happy.ishare.ShareCore
import com.happy.ishare.core.AbsFactoryShare
import com.happy.ishare.core.Platform
import com.happy.ishare.core.ShareParam

class FactoryShareFake: AbsFactoryShare {
    override fun createShareImp(builder: ShareCore.Builder): ShareCore? {
        return FakeShareImpl(ShareParam())
    }

    override val platform: Platform
        get() = Platform.UnKnown
}
