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

import com.happy.ishare.ShareCore

abstract class AbsFactoryShare {
    abstract fun createShareImp(builder: ShareCore.Builder): ShareCore?
    protected abstract fun convertShareEntity2ShareParam(entity: ShareEntity): ShareParam
    abstract val platform: Platform
    abstract val scenes: List<Scene>
}
