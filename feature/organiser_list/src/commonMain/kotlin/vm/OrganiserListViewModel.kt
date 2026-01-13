package vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mapper.toPresentation
import model.OrganiserListState
import use_case.GetOrganisersUseCase

class OrganiserListViewModel(
    private val getOrganisersUseCase: GetOrganisersUseCase
) : ViewModel() {
    private val _uiState =
        MutableStateFlow(
            OrganiserListState(
                organisers = emptyList(),
                error = null
            )
        )
    val uiState = _uiState.asStateFlow()

    init {
        getOrganisers()
    }

    private fun getOrganisers() {
        viewModelScope.launch {
            runCatching {
                getOrganisersUseCase()
            }.onSuccess { organisers ->
                _uiState.value =
                    _uiState.value.copy(
                        organisers =
                            organisers.map { organiserDomain ->
                                organiserDomain.toPresentation()
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
