package co.feip.fefu2025.domain.model

data class RepositoryScreenModel (
    val name: String,
    val description: String,
    val dataCreate: String,
    val forks: Int,
    val stars: Int,
    val icon: Int,
    val languages: List<LanguageModel>
)