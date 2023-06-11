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

package com.happy.ishare.link

import android.content.Context
import com.happy.ishare.ShareCore
import com.happy.ishare.core.AbsFactoryShare
import com.happy.ishare.core.Platform
import com.happy.ishare.core.Scene
import com.happy.ishare.core.ShareEntity

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
