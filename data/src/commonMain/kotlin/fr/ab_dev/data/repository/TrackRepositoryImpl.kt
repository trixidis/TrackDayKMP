package fr.ab_dev.data.repository

import fr.ab_dev.data.mapper.toDomain
import fr.ab_dev.data.remote.TrackDayRemoteDataSource
import model.TrackDomain
import repository.TrackRepository

class TrackRepositoryImpl(
    private val remoteDataSource: TrackDayRemoteDataSource
) : TrackRepository {
    override suspend fun getTracks(): List<TrackDomain> = remoteDataSource.getTracks().toDomain()
}
