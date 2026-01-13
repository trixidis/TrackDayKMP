package fr.ab_dev.data.remote

import fr.ab_dev.data.model.Organiser
import fr.ab_dev.data.model.TrackData

interface TrackDayRemoteDataSource {
    suspend fun getTracks(): List<TrackData>

    suspend fun getOrganisers(): List<Organiser>
}
