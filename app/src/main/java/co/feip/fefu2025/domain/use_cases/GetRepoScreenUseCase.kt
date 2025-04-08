package co.feip.fefu2025.domain.use_cases

import co.feip.fefu2025.domain.model.RepoScreenModel
import co.feip.fefu2025.domain.repository.RepoScreenRepository

class GetRepoScreenUseCase(
    private val repository: RepoScreenRepository
) {
    operator fun invoke(): RepoScreenModel {
        return repository.getRepoScreen()
    }
}