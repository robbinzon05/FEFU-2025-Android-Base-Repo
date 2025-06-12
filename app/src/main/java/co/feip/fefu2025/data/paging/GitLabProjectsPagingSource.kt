package co.feip.fefu2025.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import co.feip.fefu2025.api.GitLabApiService
import co.feip.fefu2025.domain.model.RepositoryCardModel
import retrofit2.HttpException

class GitLabProjectsPagingSource(
    private val api: GitLabApiService,
    private val starred: Boolean
) : PagingSource<Int, RepositoryCardModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryCardModel> {
        val page = params.key ?: 1
        return try {
            val resp = if (starred)
                api.getStarred(page = page, perPage = params.loadSize)
            else
                api.getProjects(page = page, perPage = params.loadSize)

            if (!resp.isSuccessful) return LoadResult.Error(HttpException(resp))

            val projects = resp.body().orEmpty().map { it.toCardModel() }
            val next = resp.headers()["X-Next-Page"]?.toIntOrNull()

            LoadResult.Page(
                data = projects,
                prevKey = if (page == 1) null else page - 1,
                nextKey = next
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RepositoryCardModel>): Int? = 1
}
