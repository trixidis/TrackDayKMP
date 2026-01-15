package fr.ab_dev.data.di

import org.koin.core.context.loadKoinModules

fun initDataLayer(useFakeData: Boolean) {
    val dataSourceModule = if (useFakeData) fakeDataSourceModule else serverDataSourceModule
    loadKoinModules(listOf(repositoriesModule, dataSourceModule))
}
