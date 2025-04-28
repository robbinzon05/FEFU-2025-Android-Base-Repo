package co.feip.fefu2025.presentation.screen.repo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.model.RepositoryScreenModel
import co.feip.fefu2025.domain.use_cases.GetRepositoryScreenUseCase
import co.feip.fefu2025.presentation.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RepositoryScreenViewModel(
    private val getRepo: GetRepositoryScreenUseCase,
    private val cardId: Int
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<UiState<RepositoryScreenModel>>(UiState.Loading)
    val uiState: StateFlow<UiState<RepositoryScreenModel>> = _uiState

    init { load() }

    fun retry() = load()

    private fun load() = viewModelScope.launch {
        _uiState.value = UiState.Loading
        runCatching { getRepo(cardId) }
            .onSuccess { _uiState.value = UiState.Success(it) }
            .onFailure { _uiState.value = UiState.Error(it) }
    }
}
