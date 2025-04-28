package co.feip.fefu2025.presentation.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.model.RepositoryCardModel
import co.feip.fefu2025.domain.use_cases.GetAllRepositoriesUseCase
import co.feip.fefu2025.domain.use_cases.GetStarredRepositoriesUseCase
import co.feip.fefu2025.presentation.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class MainData(
    val starred: List<RepositoryCardModel>,
    val all: List<RepositoryCardModel>
)

class MainPageViewModel(
    private val getStarred: GetStarredRepositoriesUseCase,
    private val getAll: GetAllRepositoriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<MainData>>(UiState.Loading)
    val uiState: StateFlow<UiState<MainData>> = _uiState

    init { load() }

    fun retry() = load()

    private fun load() = viewModelScope.launch {
        _uiState.value = UiState.Loading
        runCatching {
            MainData(
                starred = getStarred(),
                all = getAll()
            )
        }.onSuccess { _uiState.value = UiState.Success(it) }
            .onFailure { _uiState.value = UiState.Error(it) }
    }
}
