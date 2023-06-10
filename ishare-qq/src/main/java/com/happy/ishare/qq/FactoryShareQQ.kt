package com.happy.ishare.qq

import android.content.Context
import com.happy.ishare.ShareCore
import com.happy.ishare.ShareSDK
import com.happy.ishare.core.AbsFactoryShare
import com.happy.ishare.core.Platform
import com.happy.ishare.core.Scene
import com.happy.ishare.core.ShareEntity
import com.happy.ishare.core.ShareParam
import com.happy.ishare.utils.getAppMetaData
import com.tencent.tauth.Tencent

class FactoryShareQQ(context: Context) : AbsFactoryShare() {

    private val qqApi by lazy {  createQQFactory(context) }
    private fun createQQFactory(context: Context): Tencent {
        val appId = context.getAppMetaData("QQ_APPID")
        return Tencent.createInstance(appId, context, ShareSDK.authorities)
    }
    override fun convertShareEntity2ShareParam(entity: ShareEntity): QQShareParam {
        val param = entity.attachInfo as? QQShareParam
        return param ?: QQShareParam().apply {
            title = entity.title
            description = entity.description
            webPageUrl = entity.url
            thumbData = entity.thumbData
            scene = entity.scene as? QQScene ?: QQScene.Friends
        }
    }
    override fun createShareImp(builder: ShareCore.Builder): ShareCore? {
        val qqParam = convertShareEntity2ShareParam(builder.shareEntity!!)
        return when (qqParam.scene) {
            QQScene.Friends -> QQShareImp(qqApi, qqParam)
            QQScene.QZone -> QZoneShareImp(qqApi, qqParam)
        }
    }

    override val platform: Platform
        get() = QQ

    override val scenes: List<Scene>
        get() = listOf(QQScene.Friends, QQScene.QZone)
}
