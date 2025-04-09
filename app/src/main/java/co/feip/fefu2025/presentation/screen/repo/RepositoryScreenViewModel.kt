package co.feip.fefu2025.presentation.screen.repo

import androidx.lifecycle.ViewModel
import co.feip.fefu2025.domain.model.RepositoryScreenModel
import co.feip.fefu2025.domain.use_cases.GetRepositoryScreenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class RepositoryScreenUiState(
    val isLoading: Boolean = false,
    val repositoryScreenModel: RepositoryScreenModel? = null,
)

class RepositoryScreenViewModel(
    private val getRepositoryScreenUseCase: GetRepositoryScreenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RepositoryScreenUiState())
    val uiState: StateFlow<RepositoryScreenUiState> = _uiState

    init {
        loadRepo()
    }

    private fun loadRepo() {
        _uiState.update { it.copy(isLoading = true) }
        val result = getRepositoryScreenUseCase()
        _uiState.update {
            it.copy(
                isLoading = false,
                repositoryScreenModel = result
            )
        }
    }
}
