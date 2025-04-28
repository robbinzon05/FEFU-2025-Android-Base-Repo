package co.feip.fefu2025.data.repository

import co.feip.fefu2025.data.mock.DataProvider
import co.feip.fefu2025.domain.model.RepositoryScreenModel
import co.feip.fefu2025.domain.repository.RepositoryScreenRepository

class RepositoryScreenRepositoryImpl(
    private val dataProvider: DataProvider
) : RepositoryScreenRepository {

    override suspend fun getRepositoryScreen(cardId: Int): RepositoryScreenModel {
        imitateDelayAndRandomError()
        return dataProvider.getRepositoryScreen(cardId)
    }
}
