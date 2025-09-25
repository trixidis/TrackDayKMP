package use_case

import mapper.toDomain
import model.TrackDomain
import fr.ab_dev.data.tracks.remote.TracksRemoteDataSource

class GetTrackListUseCaseImpl(
    private val tracksRemoteDataSource: TracksRemoteDataSource,
) : GetTrackListUseCase {
    override suspend fun invoke(): List<TrackDomain> = tracksRemoteDataSource.getTracks().toDomain()
}