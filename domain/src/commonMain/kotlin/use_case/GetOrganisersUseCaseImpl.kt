package use_case

import fr.ab_dev.data.remote.TrackDayRemoteDataSource
import mapper.toDomain
import model.OrganiserDomain

class GetOrganisersUseCaseImpl(
    private val trackDayRemoteDataSource: TrackDayRemoteDataSource
)
    : GetOrganisersUseCase{
    override suspend fun invoke(): List<OrganiserDomain> =
        trackDayRemoteDataSource.getOrganisers().toDomain()
}