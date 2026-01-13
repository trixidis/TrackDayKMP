package fr.ab_dev.data.di

import org.koin.core.context.loadKoinModules

fun initDataLayer() {
    loadKoinModules(tracksModule)
}
