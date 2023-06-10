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
