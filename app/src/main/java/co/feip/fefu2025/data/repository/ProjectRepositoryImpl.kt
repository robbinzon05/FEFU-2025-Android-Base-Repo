package co.feip.fefu2025.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import co.feip.fefu2025.R
import co.feip.fefu2025.api.GitLabApiService
import co.feip.fefu2025.data.paging.GitLabProjectsPagingSource
import co.feip.fefu2025.domain.model.LanguageModel
import co.feip.fefu2025.domain.model.RepositoryCardModel
import co.feip.fefu2025.domain.model.RepositoryScreenModel
import co.feip.fefu2025.domain.repository.ProjectRepository

class ProjectRepositoryImpl(
    private val api: GitLabApiService
) : ProjectRepository {

    override fun pagingAll() = Pager(PagingConfig(20, enablePlaceholders = false)) {
        GitLabProjectsPagingSource(api, starred = false)
    }

    override fun pagingStars() = Pager(PagingConfig(20, enablePlaceholders = false)) {
        GitLabProjectsPagingSource(api, starred = true)
    }

    override suspend fun getProject(id: Int): RepositoryScreenModel {
        val dto = api.getProject(id).body() ?: error("empty")
        return RepositoryScreenModel(
            name        = dto.name_with_namespace,
            description = dto.description ?: "",
            forks       = dto.forks_count,
            stars       = dto.star_count,
            dataCreate  = "",
            icon        = R.drawable.ic_launcher_foreground,
            languages   = emptyList()
        )
    }

    override suspend fun search(query: String, limit: Int): List<RepositoryCardModel> {
        val resp = api.getProjects(page = 1, perPage = limit, simple = true, search = query)
        if (!resp.isSuccessful) error("Search failed: ${resp.code()}")
        return resp.body().orEmpty().map { it.toCardModel() }
    }
}

