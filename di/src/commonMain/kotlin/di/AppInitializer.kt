package di

import fr.ab_dev.data.di.initDataLayer

fun initApp() {
    initDataLayer(AppConfig.useFakeData)
    initDomainLayer()
}
