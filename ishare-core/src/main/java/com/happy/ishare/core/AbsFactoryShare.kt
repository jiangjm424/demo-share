package com.happy.ishare.core

import com.happy.ishare.ShareCore

abstract class AbsFactoryShare {
    abstract fun createShareImp(builder: ShareCore.Builder): ShareCore?
    protected abstract fun convertShareEntity2ShareParam(entity: ShareEntity): ShareParam
    abstract val platform: Platform
    abstract val scenes: List<Scene>
}
