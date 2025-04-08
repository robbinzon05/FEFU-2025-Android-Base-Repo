package co.feip.fefu2025.domain.repository

import co.feip.fefu2025.domain.model.RepoCardModel

interface RepoCardRepository {
    fun getStarredRepositories(): List<RepoCardModel>
    fun getAllRepositories(): List<RepoCardModel>
}