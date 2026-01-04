package di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import vm.TrackDetailViewModel

val trackDetailModules = module {
    viewModel<TrackDetailViewModel> { (trackName: String) ->
        TrackDetailViewModel(
            trackName = trackName,
            getTrackListUseCase = get(),
        )
    }
}