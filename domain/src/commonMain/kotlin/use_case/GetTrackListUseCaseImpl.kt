package use_case

import mapper.toDomain
import model.TrackDomain
import fr.ab_dev.data.remote.TrackDayRemoteDataSource

class GetTrackListUseCaseImpl(
    private val trackDayRemoteDataSource: TrackDayRemoteDataSource,
) : GetTrackListUseCase {
    override suspend fun invoke(): List<TrackDomain> = trackDayRemoteDataSource.getTracks().toDomain()
}