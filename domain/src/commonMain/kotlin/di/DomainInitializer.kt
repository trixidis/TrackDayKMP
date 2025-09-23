package di

import org.koin.core.context.loadKoinModules

fun initDomainLayer() {
    loadKoinModules(domainModules)
    initDataLayer()
}