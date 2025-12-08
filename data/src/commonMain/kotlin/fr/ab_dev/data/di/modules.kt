package fr.ab_dev.data.di

import de.jensklingenberg.ktorfit.Ktorfit
import fr.ab_dev.data.LocalProperties
import fr.ab_dev.data.remote.TrackDayApi
import fr.ab_dev.data.remote.createTrackDayApi
import fr.ab_dev.data.remote.TrackDayRemoteDataSourceImpl
import fr.ab_dev.data.remote.TrackDayRemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module


val tracksModule = module {
    single<TrackDayRemoteDataSource> {
        TrackDayRemoteDataSourceImpl(
            api = get(),
        )
    }

    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json()
            }
        }
    }

    single<Ktorfit> {
        Ktorfit.Builder()
            .baseUrl(LocalProperties.BASE_URL)
            .httpClient(client = get())
            .build()
    }

    single<TrackDayApi> {
        get<Ktorfit>().createTrackDayApi()
    }


}
