package fr.ab_dev.data.remote.response

import fr.ab_dev.data.model.Organiser
import kotlinx.serialization.Serializable

@Serializable
data class GetOrganiserResponse(
    val organisers: List<Organiser>,
    val nbItems: Int
)
