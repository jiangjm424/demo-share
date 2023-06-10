package com.happy.ishare.link

import com.happy.ishare.core.Platform
import com.happy.ishare.core.Scene

sealed class LinkScene : Scene {
    object CopyLink : LinkScene() {
        override val platform: Platform
            get() = com.happy.ishare.link.CopyLink
    }
}
