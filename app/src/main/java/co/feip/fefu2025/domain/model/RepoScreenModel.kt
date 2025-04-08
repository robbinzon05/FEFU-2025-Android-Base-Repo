package co.feip.fefu2025.domain.model

data class RepoScreenModel (
    val name: String,
    val description: String,
    val forks: Int,
    val stars: Int,
    val icon: Int,
    val languages: List<LanguageModel>
)