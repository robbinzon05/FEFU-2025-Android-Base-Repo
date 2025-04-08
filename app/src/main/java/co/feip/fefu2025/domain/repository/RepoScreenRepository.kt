package co.feip.fefu2025.domain.repository

import co.feip.fefu2025.domain.model.RepoScreenModel

interface RepoScreenRepository {
    fun getRepoScreen(): RepoScreenModel
}