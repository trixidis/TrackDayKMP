package fr.ab_dev.data.remote

import de.jensklingenberg.ktorfit.http.GET
import fr.ab_dev.data.remote.response.GetOrganiserResponse
import fr.ab_dev.data.remote.response.GetTrackResponse

interface TrackDayApi {
    @GET("tracks")
    suspend fun getTracks(): GetTrackResponse

    @GET("org")
    suspend fun getOrganisers(): GetOrganiserResponse
}
