package tracks.remote

import model.TrackData

interface TracksRemoteDataSource {

    suspend fun getTracks() : List<TrackData>
}