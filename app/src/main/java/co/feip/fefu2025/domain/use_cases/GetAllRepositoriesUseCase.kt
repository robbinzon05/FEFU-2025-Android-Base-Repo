package co.feip.fefu2025.domain.use_cases

import co.feip.fefu2025.domain.model.RepoCardModel
import co.feip.fefu2025.domain.repository.RepoCardRepository

class GetAllRepositoriesUseCase(
    private val repository: RepoCardRepository
) {
    operator fun invoke(): List<RepoCardModel> {
        return repository.getAllRepositories()
    }
}