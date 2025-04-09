package co.feip.fefu2025.domain.repository

import co.feip.fefu2025.domain.model.RepositoryScreenModel

interface RepositoryScreenRepository {
    fun getRepositoryScreen(): RepositoryScreenModel
}