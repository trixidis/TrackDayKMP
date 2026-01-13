package fr.ab_dev.data.model

import kotlinx.serialization.Serializable

@Serializable
data class TrackData(
    val name: String,
    val country: String,
    val iconUrl: String,
)
