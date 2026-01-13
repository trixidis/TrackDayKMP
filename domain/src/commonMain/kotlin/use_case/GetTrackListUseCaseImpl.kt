package use_case

import model.TrackDomain
import repository.TrackRepository

class GetTrackListUseCaseImpl(
    private val trackRepository: TrackRepository,
) : GetTrackListUseCase {
    override suspend fun invoke(): List<TrackDomain> = trackRepository.getTracks()
}
