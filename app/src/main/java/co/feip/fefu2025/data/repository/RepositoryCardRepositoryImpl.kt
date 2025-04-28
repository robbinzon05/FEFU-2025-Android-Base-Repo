package co.feip.fefu2025.data.repository

import co.feip.fefu2025.data.mock.DataProvider
import co.feip.fefu2025.domain.model.RepositoryCardModel
import co.feip.fefu2025.domain.repository.RepositoryCardRepository

class RepositoryCardRepositoryImpl(
    private val dataProvider: DataProvider
) : RepositoryCardRepository {

    override suspend fun getStarredRepositories(): List<RepositoryCardModel> {
        imitateDelayAndRandomError()
        return dataProvider.getStarredCards()
    }

    override suspend fun getAllRepositories(): List<RepositoryCardModel> {
        imitateDelayAndRandomError()
        return dataProvider.getAllCards()
    }

    override suspend fun searchRepositories(query: String): List<RepositoryCardModel> {
        imitateDelayAndRandomError()
        return dataProvider.getAllCards()
            .filter { it.name.contains(query, ignoreCase = true) }
    }
}
