package co.feip.fefu2025.data.mock

import co.feip.fefu2025.R
import co.feip.fefu2025.domain.model.LanguageModel
import co.feip.fefu2025.domain.model.RepositoryCardModel
import co.feip.fefu2025.domain.model.RepositoryScreenModel
import kotlin.random.Random

private data class RepoTemplate(
    val name: String,
    val description: String,
    val forks: Int,
    val stars: Int,
    val languages: List<LanguageModel>
)

class DataProvider {

    private val examples = listOf(
        RepoTemplate(
            name = "First Project",
            description = "This is my first project! Please, don't be so tough, this is my first step in programming!.",
            forks = 1, stars = 1,
            languages = listOf(
                LanguageModel("Kotlin", 92f, 0xFF7F52FF),
                LanguageModel("Java", 8f, 0xFFB07219)
            )
        ),
        RepoTemplate(
            name = "Template_labs_fefu",
            description = "Templates for labs in fefu.",
            forks = 456, stars = 11,
            languages = listOf(
                LanguageModel("JavaScript", 88f, 0xFFF1E05A),
                LanguageModel("TypeScript", 12f, 0xFF2B7489)
            )
        ),
        RepoTemplate(
            name = "Simple Calendar",
            description = "Very simple calendar, nothing interesting...",
            forks = 241, stars = 1424,
            languages = listOf(
                LanguageModel("C", 98f, 0xFF555555),
                LanguageModel("ASM", 2f, 0xFF6E4C13)
            )
        ),
        RepoTemplate(
            name = "The App",
            description = "The App for geeks and no more!",
            forks = 13, stars = 56,
            languages = listOf(
                LanguageModel("Dart", 96f, 0xFF00B4AB),
                LanguageModel("C++", 4f, 0xFFF34B7D)
            )
        ),
        RepoTemplate(
            name = "Game.exe",
            description = "First game ever in my life",
            forks = 45, stars = 5741,
            languages = listOf(
                LanguageModel("Swift", 97f, 0xFFFFAC45),
                LanguageModel("C++", 3f, 0xFFF34B7D)
            )
        )
    )

    private val allRepos: List<RepositoryCardModel> = buildList {
        examples.forEachIndexed { idx, t ->
            add(
                RepositoryCardModel(
                    id = idx,
                    name = t.name,
                    description = t.description,
                    forks = t.forks,
                    stars = t.stars,
                    icon = R.drawable.ic_launcher_foreground
                )
            )
        }
        val baseId = size
        repeat(15) { n ->
            add(
                RepositoryCardModel(
                    id = baseId + n,
                    name = "Demo-Project-${n + 1}",
                    description = "Experimental demo project number ${n + 1}.",
                    forks = Random.nextInt(0, 500),
                    stars = Random.nextInt(0, 2000),
                    icon = R.drawable.ic_launcher_foreground
                )
            )
        }
    }

    private fun randomDate(): String {
        val y = 2015 + Random.nextInt(0, 10)
        val m = 1 + Random.nextInt(0, 12)
        val d = 1 + Random.nextInt(0, 28)
        return "%04d-%02d-%02d".format(y, m, d)
    }


    fun getRepositoryScreen(cardId: Int): RepositoryScreenModel {
        val card = allRepos.firstOrNull { it.id == cardId } ?: allRepos.first()
        val langs = if (cardId < examples.size) {
            examples[cardId].languages
        } else {
            listOf(
                LanguageModel("Kotlin", 70f, 0xFF7F52FF),
                LanguageModel("XML", 30f, 0xFFFFC107)
            )
        }
        return RepositoryScreenModel(
            name = card.name,
            description = card.description,
            dataCreate = randomDate(),
            forks = card.forks,
            stars = card.stars,
            icon = card.icon,
            languages = langs
        )
    }

    fun getStarredCards(): List<RepositoryCardModel> =
        allRepos.shuffled().take(5)

    fun getAllCards(): List<RepositoryCardModel> = allRepos
}
