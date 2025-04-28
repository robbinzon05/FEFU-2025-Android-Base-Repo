package co.feip.fefu2025.domain.use_cases

import co.feip.fefu2025.domain.model.RepositoryCardModel
import co.feip.fefu2025.domain.repository.RepositoryCardRepository

class SearchRepositoriesUseCase(
    private val repository: RepositoryCardRepository
) {
    suspend operator fun invoke(query: String): List<RepositoryCardModel> =
        repository.searchRepositories(query)
}
