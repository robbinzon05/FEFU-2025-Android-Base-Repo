package co.feip.fefu2025.domain.use_cases

import co.feip.fefu2025.domain.model.RepositoryScreenModel
import co.feip.fefu2025.domain.repository.RepositoryScreenRepository

class GetRepositoryScreenUseCase(
    private val repository: RepositoryScreenRepository
) {
    suspend operator fun invoke(cardId: Int): RepositoryScreenModel =
        repository.getRepositoryScreen(cardId)
}
