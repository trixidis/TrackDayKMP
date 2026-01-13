package vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import model.BottomNavBarFeature
import model.BottomNavBarState
import model.NavigationStore

class BottomNavBarViewModel(
    private val navigationStore: NavigationStore
) : ViewModel() {
    fun onTrackSelected() {
        navigationStore.select(BottomNavBarFeature.Tracks)
    }

    fun onOrganisersSelected() {
        navigationStore.select(BottomNavBarFeature.Organisers)
    }

    val uiState =
        navigationStore.feature
            .map { feature ->
                BottomNavBarState(featureSelected = feature)
            }.stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                BottomNavBarState(BottomNavBarFeature.Tracks)
            )
}
