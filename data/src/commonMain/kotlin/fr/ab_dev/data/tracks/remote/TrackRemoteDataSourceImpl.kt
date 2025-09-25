package fr.ab_dev.data.tracks.remote

import fr.ab_dev.data.model.TrackData
import fr.ab_dev.data.remote.TrackApi

class TrackRemoteDataSourceImpl(
    val trackApi: TrackApi
) : TracksRemoteDataSource {
    override suspend fun getTracks(): List<TrackData> =
        trackApi.getTracks().tracks
}