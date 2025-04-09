package co.feip.fefu2025.domain.repository

import co.feip.fefu2025.domain.model.RepositoryCardModel

interface RepositoryCardRepository {
    fun getStarredRepositories(): List<RepositoryCardModel>
    fun getAllRepositories(): List<RepositoryCardModel>
}