package co.feip.fefu2025.data.repository

import co.feip.fefu2025.data.mock.DataProvider
import co.feip.fefu2025.domain.model.RepoScreenModel
import co.feip.fefu2025.domain.repository.RepoScreenRepository

class RepoScreenRepositoryImpl(
    private val repoData: DataProvider
): RepoScreenRepository {

    override fun getRepoScreen(): RepoScreenModel {
        return repoData.getRepoScreen()
    }
}