package di

import org.koin.dsl.module
import tracks.remote.TrackFakeRemoteDataSourceImpl
import tracks.remote.TracksRemoteDataSource


val tracksModule = module {
    single<TracksRemoteDataSource> {
        TrackFakeRemoteDataSourceImpl()
    }

}