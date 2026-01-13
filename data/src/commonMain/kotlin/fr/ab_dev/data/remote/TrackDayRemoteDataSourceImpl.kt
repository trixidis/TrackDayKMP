package fr.ab_dev.data.remote

import fr.ab_dev.data.model.Organiser
import fr.ab_dev.data.model.TrackData

class TrackDayRemoteDataSourceImpl(
    val api: TrackDayApi
) : TrackDayRemoteDataSource {
    override suspend fun getTracks(): List<TrackData> = api.getTracks().tracks

    override suspend fun getOrganisers(): List<Organiser> = api.getOrganisers().organisers
}
