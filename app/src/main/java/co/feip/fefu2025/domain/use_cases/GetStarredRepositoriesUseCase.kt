package co.feip.fefu2025.domain.use_cases

import co.feip.fefu2025.domain.model.RepoCardModel
import co.feip.fefu2025.domain.repository.RepoCardRepository

class GetStarredRepositoriesUseCase(
    private val repoCardRepo: RepoCardRepository
) {
    operator fun invoke(): List<RepoCardModel> {
        return repoCardRepo.getStarredRepositories()
    }
}