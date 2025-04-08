package co.feip.fefu2025.data.repository

import co.feip.fefu2025.data.mock.DataProvider
import co.feip.fefu2025.domain.model.RepoCardModel
import co.feip.fefu2025.domain.repository.RepoCardRepository

class RepoCardRepositoryImpl(
    private val repoData: DataProvider
): RepoCardRepository {

    override fun getStarredRepositories(): List<RepoCardModel> {
        return repoData.getStarredCards()
    }

    override fun getAllRepositories(): List<RepoCardModel> {
        return repoData.getAllCards()
    }
}