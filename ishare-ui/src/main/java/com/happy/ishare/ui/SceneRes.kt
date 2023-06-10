package com.happy.ishare.ui

import com.happy.ishare.link.LinkScene
import com.happy.ishare.qq.QQScene
import com.happy.ishare.wechat.WechatScene

internal val sceneResMap = mapOf(
    LinkScene.CopyLink to SceneEntry(
        LinkScene.CopyLink,
        R.drawable.ic_copy_link,
        R.string.copy_link
    ),
    QQScene.Friends to SceneEntry(QQScene.Friends, R.drawable.ic_qq_friend, R.string.qq_friend),
    QQScene.QZone to SceneEntry(QQScene.QZone, R.drawable.ic_qq_qzone, R.string.qq_qzone),
    WechatScene.Session to SceneEntry(
        WechatScene.Session,
        R.drawable.ic_wechat_session,
        R.string.wechat_session
    ),
    WechatScene.Favorite to SceneEntry(
        WechatScene.Favorite,
        R.drawable.ic_wechat_favorite,
        R.string.wechat_favorite
    ),
    WechatScene.Timeline to SceneEntry(
        WechatScene.Timeline,
        R.drawable.ic_wechat_timeline,
        R.string.wechat_timeline
    ),
)
