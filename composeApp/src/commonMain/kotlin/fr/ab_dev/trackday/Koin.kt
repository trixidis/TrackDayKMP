package fr.ab_dev.trackday

import di.initNavigationFeature
import di.initTrackListFeature
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        initTrackListFeature()
        initNavigationFeature()
    }