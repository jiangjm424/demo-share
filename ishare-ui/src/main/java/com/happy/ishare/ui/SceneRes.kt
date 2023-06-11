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

package com.happy.ishare.ui

import com.happy.ishare.link.LinkScene
import com.happy.ishare.qq.QQScene
import com.happy.ishare.wechat.WechatScene

internal val sceneResMap = mapOf(
    LinkScene.CopyLink to SceneEntry(
        LinkScene.CopyLink,
        R.drawable.ic_copy_link,
        R.string.copy_link,
    ),
    QQScene.Friends to SceneEntry(QQScene.Friends, R.drawable.ic_qq_friend, R.string.qq_friend),
    QQScene.QZone to SceneEntry(QQScene.QZone, R.drawable.ic_qq_qzone, R.string.qq_qzone),
    WechatScene.Session to SceneEntry(
        WechatScene.Session,
        R.drawable.ic_wechat_session,
        R.string.wechat_session,
    ),
    WechatScene.Favorite to SceneEntry(
        WechatScene.Favorite,
        R.drawable.ic_wechat_favorite,
        R.string.wechat_favorite,
    ),
    WechatScene.Timeline to SceneEntry(
        WechatScene.Timeline,
        R.drawable.ic_wechat_timeline,
        R.string.wechat_timeline,
    ),
)
