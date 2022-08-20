package com.happy.ishare.core

import com.happy.ishare.ShareCore

interface AbsFactoryShare {

    fun createShareImp(builder: ShareCore.Builder): ShareCore?
    val platform: Platform
}
