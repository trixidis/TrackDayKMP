package di

import org.koin.core.context.loadKoinModules

fun initTrackListFeature() {
    loadKoinModules(trackListModules)
    initApp()
}
