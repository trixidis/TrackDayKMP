package di

import org.koin.core.context.loadKoinModules

fun initDataLayer() {
    loadKoinModules(tracksModule)

}