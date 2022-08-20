package com.happy.ishare.qq

import com.happy.ishare.ShareCore
import com.happy.ishare.core.AbsFactoryShare
import com.happy.ishare.core.Platform
import com.tencent.tauth.Tencent

class FactoryShareQQ(private val qqApi: Tencent) : AbsFactoryShare {
    override fun createShareImp(builder: ShareCore.Builder): ShareCore? {
        val qqParam = builder.shareParam as QQShareParam
        return when (qqParam.scene) {
            QQScene.QQ -> QQShareImp(qqApi, qqParam)
            QQScene.QZone -> QZoneShareImp(qqApi, qqParam)
        }
    }

    override val platform: Platform
        get() = Platform.QQ
}
