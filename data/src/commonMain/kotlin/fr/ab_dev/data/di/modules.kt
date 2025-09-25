package fr.ab_dev.data.di

import de.jensklingenberg.ktorfit.Ktorfit
import fr.ab_dev.data.LocalProperties
import fr.ab_dev.data.remote.TrackApi
import fr.ab_dev.data.remote.createTrackApi
import fr.ab_dev.data.tracks.remote.TrackRemoteDataSourceImpl
import fr.ab_dev.data.tracks.remote.TracksRemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module


val tracksModule = module {
    single<TracksRemoteDataSource> {
        TrackRemoteDataSourceImpl(
            trackApi = get(),
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

    single<TrackApi> {
        get<Ktorfit>().createTrackApi()
    }


}
