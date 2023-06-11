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

package com.happy.ishare.fakeshare

import android.util.Log
import com.happy.ishare.ShareCore
import com.happy.ishare.core.ShareParam

class FakeShareImpl(para: ShareParam) : ShareCore(para) {
    override fun shareInner(callback: ShareCallback?) {
        Log.i(TAG, "fake share")
    }

    override fun checkParam(callback: ShareCallback?): Boolean {
        return true
    }
}
