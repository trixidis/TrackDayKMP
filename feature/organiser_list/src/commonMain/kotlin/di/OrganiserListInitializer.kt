package di

import org.koin.core.context.loadKoinModules

fun initOrganiserListFeature() {
    loadKoinModules(organizerListModule)
    initDomainLayer()
}