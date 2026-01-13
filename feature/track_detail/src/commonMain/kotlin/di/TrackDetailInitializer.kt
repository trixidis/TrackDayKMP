package di

import org.koin.core.context.loadKoinModules

fun initTrackDetailFeature() {
    loadKoinModules(trackDetailModules)
}
