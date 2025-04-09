package co.feip.fefu2025.domain.use_cases

import co.feip.fefu2025.domain.model.RepositoryCardModel
import co.feip.fefu2025.domain.repository.RepositoryCardRepository

class GetAllRepositoriesUseCase(
    private val repository: RepositoryCardRepository
) {
    operator fun invoke(): List<RepositoryCardModel> {
        return repository.getAllRepositories()
    }
}