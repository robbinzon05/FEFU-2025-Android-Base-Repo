package co.feip.fefu2025.domain.use_cases

import co.feip.fefu2025.domain.repository.ProjectRepository

class GetStarredPagingUseCase(
    private val repo: ProjectRepository
) {
    operator fun invoke() = repo.pagingStars().flow
}
