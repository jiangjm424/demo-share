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

package com.happy.ishare.qq

import android.app.Activity
import com.happy.ishare.core.ShareParam
import com.tencent.connect.share.QQShare

// 目前定义的分享内容这里由于在qq的sdk中qq与qzone对分享内容的值定义分开了。
// 因为目前是一样的值 ，所以可以这样处理
// 如果后面有变化导致不能处理，那么就需要在这里面分别再加上了
sealed class QQContent(val type: Int) {
    object DEFAULT : QQContent(QQShare.SHARE_TO_QQ_TYPE_DEFAULT)
    object AUDIO : QQContent(QQShare.SHARE_TO_QQ_TYPE_AUDIO)
    object IMAGE : QQContent(QQShare.SHARE_TO_QQ_TYPE_IMAGE)
    object MINIPROGRAM : QQContent(QQShare.SHARE_TO_QQ_MINI_PROGRAM)
}

class QQShareParam : ShareParam() {
    var audioUrl: String? = null
    var imageUrl: String? = null
    var webPageUrl: String? = null
    var host: Activity? = null
    var miniProgramId: String? = null
    var miniProgramPath: String? = null
    var scene: QQScene = QQScene.Friends
    var contentType: QQContent = QQContent.DEFAULT
}
