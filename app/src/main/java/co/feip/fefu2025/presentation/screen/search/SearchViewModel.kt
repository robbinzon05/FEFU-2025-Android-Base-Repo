package co.feip.fefu2025.presentation.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.model.RepositoryCardModel
import co.feip.fefu2025.domain.use_cases.SearchRepositoriesUseCase
import co.feip.fefu2025.presentation.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: SearchRepositoriesUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<UiState<List<RepositoryCardModel>>>(UiState.Success(emptyList()))
    val uiState: StateFlow<UiState<List<RepositoryCardModel>>> = _uiState

    fun onQueryChange(query: String) = viewModelScope.launch {
        if (query.isBlank()) {
            _uiState.value = UiState.Success(emptyList())
            return@launch
        }
        _uiState.value = UiState.Loading
        runCatching { searchUseCase(query) }
            .onSuccess { _uiState.value = UiState.Success(it) }
            .onFailure { _uiState.value = UiState.Error(it) }
    }
}
