package co.feip.fefu2025.domain.use_cases

import co.feip.fefu2025.domain.repository.ProjectRepository

class GetProjectsPagingUseCase(
    private val repo: ProjectRepository
) {
    operator fun invoke() = repo.pagingAll().flow
}
