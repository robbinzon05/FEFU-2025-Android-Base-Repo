package co.feip.fefu2025.domain.repository

import co.feip.fefu2025.domain.model.RepositoryCardModel

interface RepositoryCardRepository {
    suspend fun getStarredRepositories(): List<RepositoryCardModel>
    suspend fun getAllRepositories(): List<RepositoryCardModel>
    suspend fun searchRepositories(query: String): List<RepositoryCardModel>
}
