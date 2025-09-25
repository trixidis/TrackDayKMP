package fr.ab_dev.data.tracks.remote

import fr.ab_dev.data.model.TrackData

interface TracksRemoteDataSource {

    suspend fun getTracks() : List<TrackData>
}