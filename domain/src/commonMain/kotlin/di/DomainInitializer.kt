package di

import fr.ab_dev.data.di.initDataLayer
import org.koin.core.context.loadKoinModules

fun initDomainLayer() {
    loadKoinModules(domainModules)
    initDataLayer()
}