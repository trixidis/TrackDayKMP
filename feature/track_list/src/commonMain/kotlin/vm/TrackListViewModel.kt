package vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mappers.toPresentation
import ui.TrackListState
import use_case.GetTrackListUseCase

class TrackListViewModel(
    private val getTrackListUseCase: GetTrackListUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(TrackListState(emptyList(), null))
    val uiState: StateFlow<TrackListState> = _uiState.asStateFlow()

    init {
        getTracks()
    }

    private fun getTracks() {
        viewModelScope.launch {
            runCatching {
                getTrackListUseCase()
            }.onSuccess { tracks ->
                _uiState.value =
                    _uiState.value.copy(
                        tracks =
                            tracks.map { trackDomain ->
                                trackDomain.toPresentation()
                            }
                    )
            }.onFailure { error ->
                _uiState.value =
                    _uiState.value.copy(
                        error = error
                    )
            }
        }
    }
}
