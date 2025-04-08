package co.feip.fefu2025.presentation.screen.repo

import androidx.lifecycle.ViewModel
import co.feip.fefu2025.domain.model.RepoScreenModel
import co.feip.fefu2025.domain.use_cases.GetRepoScreenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class RepoUiState(
    val isLoading: Boolean = false,
    val repoScreenModel: RepoScreenModel? = null,
)

class RepoViewModel(
    private val getRepoScreenUseCase: GetRepoScreenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RepoUiState())
    val uiState: StateFlow<RepoUiState> = _uiState

    init {
        loadRepo()
    }

    private fun loadRepo() {
        _uiState.update { it.copy(isLoading = true) }
        val result = getRepoScreenUseCase()
        _uiState.update {
            it.copy(
                isLoading = false,
                repoScreenModel = result
            )
        }
    }
}
