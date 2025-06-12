package co.feip.fefu2025.domain.use_cases

import co.feip.fefu2025.domain.model.RepositoryScreenModel
import co.feip.fefu2025.domain.repository.ProjectRepository

class GetRepositoryScreenUseCase(
    private val repo: ProjectRepository
) {
    suspend operator fun invoke(id: Int) = repo.getProject(id)
}