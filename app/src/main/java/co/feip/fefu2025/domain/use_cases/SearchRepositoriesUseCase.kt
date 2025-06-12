package co.feip.fefu2025.domain.use_cases

import co.feip.fefu2025.domain.model.RepositoryCardModel
import co.feip.fefu2025.domain.repository.ProjectRepository

class SearchRepositoriesUseCase(
    private val repo: ProjectRepository
) {
    suspend operator fun invoke(query: String): List<RepositoryCardModel> =
        repo.search(query)
}
