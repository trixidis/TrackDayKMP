package di

import model.NavigationStore
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import vm.BottomNavBarViewModel


val navigationModule = module {
    viewModel<BottomNavBarViewModel> {
        BottomNavBarViewModel(navigationStore = get())
    }

    single<NavigationStore> {
        NavigationStore()
    }

}