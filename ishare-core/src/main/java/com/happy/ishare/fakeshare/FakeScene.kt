package com.happy.ishare.fakeshare

import com.happy.ishare.core.Platform
import com.happy.ishare.core.Scene

sealed class FakeScene : Scene {
    object UnKnow : FakeScene() {
        override val platform: Platform
            get() = UnKnown
    }
}
