package use_case

import model.TrackDomain

interface GetTrackListUseCase {

    suspend operator fun invoke():List<TrackDomain>
}