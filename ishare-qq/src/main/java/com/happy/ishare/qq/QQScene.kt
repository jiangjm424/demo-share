package com.happy.ishare.qq

import com.happy.ishare.core.Platform
import com.happy.ishare.core.Scene

sealed class QQScene(val scene: Int) : Scene {
    object Friends : QQScene(0) {
        override val platform: Platform
            get() = QQ
    }

    object QZone : QQScene(1) {
        override val platform: Platform
            get() = QQ
    }
}
