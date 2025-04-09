package co.feip.fefu2025.domain.use_cases

import co.feip.fefu2025.domain.model.RepositoryCardModel
import co.feip.fefu2025.domain.repository.RepositoryCardRepository

class GetStarredRepositoriesUseCase(
    private val repositoryCardRepository: RepositoryCardRepository
) {
    operator fun invoke(): List<RepositoryCardModel> {
        return repositoryCardRepository.getStarredRepositories()
    }
}