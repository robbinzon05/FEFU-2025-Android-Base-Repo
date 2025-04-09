package co.feip.fefu2025.data.mock

import co.feip.fefu2025.R
import co.feip.fefu2025.domain.model.LanguageModel
import co.feip.fefu2025.domain.model.RepositoryCardModel
import co.feip.fefu2025.domain.model.RepositoryScreenModel

class DataProvider {
    fun getRepositoryScreen(): RepositoryScreenModel {
        return RepositoryScreenModel(
            name = "ExampleGitLab.org/GitLab Community",
            description = "GitLab Community Edition (CE) is a" +
                    "n open source end-to-end software developm" +
                    "ent platform with built-in version control, " +
                    "issue tracking, code review, CI/CD, and more." +
                    " Self-host GitLab CE on your own servers, in a container, or on a cloud provider",
            dataCreate = "2025-03-25",
            forks = 4774,
            stars= 5407,
            icon = R.drawable.ic_launcher_foreground,
            languages = listOf<LanguageModel>(
                LanguageModel("Python",57f, 0xFF3572A5),
                LanguageModel("Rust",30f, 0xFFDEA584),
                LanguageModel("C++",13f, 0xFFF34B7D)
            )
        )
    }

    fun getStarredCards(): List<RepositoryCardModel> {
        return List<RepositoryCardModel>(10) {
            RepositoryCardModel(
                name = "ExampleGitLab.org/GitLab Community",
                description = "GitLab Community Edition (CE) is a" +
                        "n open source end-to-end software developm" +
                        "ent platform with built-in version control, " +
                        "issue tracking, code review, CI/CD, and more." +
                        " Self-host GitLab CE on your own servers, in a container, or on a cloud provider",
                forks = 4774,
                stars = 5407,
                icon = R.drawable.ic_launcher_foreground,
            )
        }
    }

    fun getAllCards(): List<RepositoryCardModel> {
        return List<RepositoryCardModel>(20){
            RepositoryCardModel(
                name = "ExampleGitLab.org/GitLab Community",
                description = "GitLab Community Edition (CE) is a" +
                        "n open source end-to-end software developm" +
                        "ent platform with built-in version control, " +
                        "issue tracking, code review, CI/CD, and more." +
                        " Self-host GitLab CE on your own servers, in a container, or on a cloud provider",
                forks = 4774,
                stars = 5407,
                icon = R.drawable.ic_launcher_foreground,
            )
        }
    }
}