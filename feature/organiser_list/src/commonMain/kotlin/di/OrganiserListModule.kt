package di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import vm.OrganiserListViewModel

val organizerListModule =
    module {

        viewModel<OrganiserListViewModel> {
            OrganiserListViewModel(getOrganisersUseCase = get())
        }
    }
