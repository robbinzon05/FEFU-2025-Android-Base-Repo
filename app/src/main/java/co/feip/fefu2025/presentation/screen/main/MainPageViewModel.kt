package co.feip.fefu2025.presentation.screen.main

import androidx.lifecycle.ViewModel
import co.feip.fefu2025.domain.model.RepositoryCardModel
import co.feip.fefu2025.domain.use_cases.GetAllRepositoriesUseCase
import co.feip.fefu2025.domain.use_cases.GetStarredRepositoriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class MainUIState(
    val starredRepositories: List<RepositoryCardModel> = emptyList(),
    val allRepositories: List<RepositoryCardModel> = emptyList(),
    val isLoading: Boolean = false
)

class MainPageViewModel (
    private val getStarredRepositoriesUseCase: GetStarredRepositoriesUseCase,
    private val getAllRepositoriesUseCase: GetAllRepositoriesUseCase
): ViewModel(){

    private val _uiState = MutableStateFlow(MainUIState())
    val uiState: StateFlow<MainUIState> = _uiState

    init {
        loadRepositories()
    }

    private fun loadRepositories() {
        _uiState.value = _uiState.value.copy(isLoading = true)

        val starredRepos = getStarredRepositoriesUseCase()
        val allRepos = getAllRepositoriesUseCase()

        _uiState.value = MainUIState(
            starredRepositories = starredRepos,
            allRepositories = allRepos,
            isLoading = false
        )
    }
}
