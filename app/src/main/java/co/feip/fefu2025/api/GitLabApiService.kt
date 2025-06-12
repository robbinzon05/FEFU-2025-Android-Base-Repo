package co.feip.fefu2025.api

import co.feip.fefu2025.dto.GitLabProjectDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitLabApiService {

    @GET("projects")
    suspend fun getProjects(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 20,
        @Query("order_by") order: String = "last_activity_at",
        @Query("simple") simple: Boolean = true,
        @Query("search") search: String? = null
    ): Response<List<GitLabProjectDto>>

    @GET("projects")
    suspend fun getStarred(
        @Query("starred") starred: Boolean = true,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 20,
        @Query("simple") simple: Boolean = true
    ): Response<List<GitLabProjectDto>>

    @GET("projects/{id}")
    suspend fun getProject(
        @Path("id") id: Int
    ): Response<GitLabProjectDto>
}

