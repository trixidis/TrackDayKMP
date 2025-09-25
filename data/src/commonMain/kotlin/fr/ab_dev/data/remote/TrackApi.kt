package fr.ab_dev.data.remote

import de.jensklingenberg.ktorfit.http.GET
import fr.ab_dev.data.tracks.remote.response.GetTrackResponse

interface TrackApi {
    @GET("tracks")
    suspend fun getTracks() : GetTrackResponse
}