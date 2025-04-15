package co.feip.fefu2025.presentation.screen.starred

import androidx.lifecycle.ViewModel
import co.feip.fefu2025.domain.model.RepositoryCardModel
import co.feip.fefu2025.domain.use_cases.GetStarredRepositoriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class StarredRepositoriesUiState(
    val starredRepositories: List<RepositoryCardModel> = emptyList(),
    val isLoading: Boolean = false
)

class StarredRepositoriesViewModel(
    private val getStarredRepositoriesUseCase: GetStarredRepositoriesUseCase
): ViewModel(){
    private val _uiState = MutableStateFlow(StarredRepositoriesUiState())
    val uiState: StateFlow<StarredRepositoriesUiState> = _uiState

    init {
        loadRepositories()
    }

    private fun loadRepositories() {
        _uiState.value = uiState.value.copy(isLoading = true)
        val starredRepositories = getStarredRepositoriesUseCase()

        _uiState.value = StarredRepositoriesUiState(
            starredRepositories = starredRepositories,
            isLoading = false
        )
    }
}