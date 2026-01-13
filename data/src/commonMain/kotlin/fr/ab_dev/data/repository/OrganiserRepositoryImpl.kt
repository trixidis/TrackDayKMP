package fr.ab_dev.data.repository

import fr.ab_dev.data.mapper.toDomain
import fr.ab_dev.data.remote.TrackDayRemoteDataSource
import model.OrganiserDomain
import repository.OrganiserRepository

class OrganiserRepositoryImpl(
    private val remoteDataSource: TrackDayRemoteDataSource
) : OrganiserRepository {
    override suspend fun getOrganisers(): List<OrganiserDomain> = remoteDataSource.getOrganisers().toDomain()
}
