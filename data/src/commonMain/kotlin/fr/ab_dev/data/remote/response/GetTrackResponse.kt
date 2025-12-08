package fr.ab_dev.data.remote.response

import fr.ab_dev.data.model.TrackData
import kotlinx.serialization.Serializable

@Serializable
data class GetTrackResponse(
    val tracks: List<TrackData>,
    val nbItems: Int
)