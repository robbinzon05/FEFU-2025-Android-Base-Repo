package co.feip.fefu2025.dto

import co.feip.fefu2025.R
import co.feip.fefu2025.domain.model.RepositoryCardModel

data class GitLabProjectDto(
    val id: Int,
    val name_with_namespace: String,
    val description: String? = "",
    val forks_count: Int,
    val star_count: Int,
    val avatar_url: String? = null,
) {
    fun toCardModel() = RepositoryCardModel(
        id = id,
        name = name_with_namespace,
        description = description ?: "",
        forks = forks_count,
        stars = star_count,
        icon = R.drawable.ic_launcher_foreground
    )
}
