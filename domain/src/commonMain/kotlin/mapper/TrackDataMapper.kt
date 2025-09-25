package mapper

import fr.ab_dev.data.model.TrackData
import model.TrackDomain

fun TrackData.toDomain() = TrackDomain(
    this.name,
    this.country,
    this.iconUrl
)

fun List<TrackData>.toDomain() = this.map { track ->
    track.toDomain()
}