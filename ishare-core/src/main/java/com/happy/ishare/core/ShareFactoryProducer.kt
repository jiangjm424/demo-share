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

package com.happy.ishare.core

class ShareFactoryProducer(extFactories: List<AbsFactoryShare>?) {
    private val shareFactories = mutableListOf<AbsFactoryShare>()

    init {
        extFactories?.forEach {
            shareFactories.add(it)
        }
    }

    fun getShareFactory(platform: Platform): AbsFactoryShare? {
        return shareFactories.firstOrNull {
            it.platform == platform
        }
    }

    internal fun supportScenes(): List<Scene> {
        val scenes = mutableListOf<Scene>()
        shareFactories.forEach {
            scenes.addAll(it.scenes)
        }
        return scenes
    }
}
