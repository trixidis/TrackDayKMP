package di

import org.koin.core.context.loadKoinModules

fun initNavigationFeature() {
    loadKoinModules(navigationModule)
    initDomainLayer()
}