package fr.ab_dev.trackday

import di.AppConfig
import di.initNavigationFeature
import di.initOrganiserListFeature
import di.initTrackDetailFeature
import di.initTrackListFeature
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    useFakeData: Boolean = false,
    appDeclaration: KoinAppDeclaration = {},
) {
    AppConfig.configure(useFakeData)
    startKoin {
        appDeclaration()
    }
    initTrackListFeature()
    initTrackDetailFeature()
    initNavigationFeature()
    initOrganiserListFeature()
}
