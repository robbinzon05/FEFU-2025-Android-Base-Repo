package co.feip.fefu2025.data.repository

import co.feip.fefu2025.data.mock.DataProvider
import co.feip.fefu2025.domain.model.RepositoryCardModel
import co.feip.fefu2025.domain.repository.RepositoryCardRepository

class RepositoryCardRepositoryImpl(
    private val repositoryCardData: DataProvider
): RepositoryCardRepository {

    override fun getStarredRepositories(): List<RepositoryCardModel> {
        return repositoryCardData.getStarredCards()
    }

    override fun getAllRepositories(): List<RepositoryCardModel> {
        return repositoryCardData.getAllCards()
    }
}