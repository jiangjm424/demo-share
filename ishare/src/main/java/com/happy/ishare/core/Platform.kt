package com.happy.ishare.core


sealed class Platform(val name: String) {
    object QQ : Platform("QQ")
    object WeChat : Platform("WeChat")
    object CopyLink : Platform("CopyLink")
    object UnKnown : Platform("UnKnown")
}
