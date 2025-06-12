package co.feip.fefu2025.domain.repository

import androidx.paging.Pager
import co.feip.fefu2025.domain.model.RepositoryCardModel
import co.feip.fefu2025.domain.model.RepositoryScreenModel

interface ProjectRepository {
    fun pagingAll(): Pager<Int, RepositoryCardModel>
    fun pagingStars(): Pager<Int, RepositoryCardModel>
    suspend fun getProject(id: Int): RepositoryScreenModel
    suspend fun search(query: String, limit: Int = 100): List<RepositoryCardModel>
}
