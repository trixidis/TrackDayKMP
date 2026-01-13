package vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mappers.toDetailPresentation
import ui.TrackDetailState
import use_case.GetTrackListUseCase

class TrackDetailViewModel(
    private val trackName: String,
    private val getTrackListUseCase: GetTrackListUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(TrackDetailState())
    val uiState: StateFlow<TrackDetailState> = _uiState.asStateFlow()

    init {
        loadTrackDetails()
    }

    private fun loadTrackDetails() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            runCatching {
                getTrackListUseCase()
            }.onSuccess { tracks ->
                val track = tracks.find { it.name == trackName }
                _uiState.value =
                    _uiState.value.copy(
                        track = track?.toDetailPresentation(),
                        isLoading = false
                    )
            }.onFailure { error ->
                _uiState.value =
                    _uiState.value.copy(
                        isLoading = false,
                        error = error
                    )
            }
        }
    }
}
