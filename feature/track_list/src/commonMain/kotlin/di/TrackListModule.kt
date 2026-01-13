package di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import vm.TrackListViewModel

val trackListModules =
    module {
        viewModel<TrackListViewModel> {
            TrackListViewModel(
                getTrackListUseCase = get(),
            )
        }
    }
