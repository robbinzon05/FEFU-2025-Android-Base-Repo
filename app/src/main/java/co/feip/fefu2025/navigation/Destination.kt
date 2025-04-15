package co.feip.fefu2025.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Destination(val route: String) {
    data object MainPage: Destination("main")
    data object RepositoryCard: Destination("repository_card") {

        const val ARG_CARD_ID = "card_id"

        val routeWithArgs = "$route/{$ARG_CARD_ID}"

        val arguments = listOf(
            navArgument(ARG_CARD_ID){ type = NavType.IntType }
        )
    }
    data object StarredRepositoriesPage: Destination("starred_page")
}