package co.feip.fefu2025.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import co.feip.fefu2025.navigation.Destination.RepositoryCard.ARG_CARD_ID
import co.feip.fefu2025.presentation.screen.main.MainPage
import co.feip.fefu2025.presentation.screen.main.MainPageViewModel
import co.feip.fefu2025.presentation.screen.repo.RepositoryScreen
import co.feip.fefu2025.presentation.screen.repo.RepositoryScreenViewModel
import co.feip.fefu2025.presentation.screen.search.SearchScreen
import co.feip.fefu2025.presentation.screen.search.SearchViewModel
import co.feip.fefu2025.presentation.screen.starred.StarredRepositoriesScreen
import co.feip.fefu2025.presentation.screen.starred.StarredRepositoriesViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Destination.MainPage.route
    ) {
        composable(Destination.MainPage.route) {
            val viewModel: MainPageViewModel = getViewModel()
            MainPage(
                viewModel = viewModel,
                onRepoClick = { cardId ->
                    navHostController.navigate("${Destination.RepositoryCard.route}/$cardId")
                },
                navToSearch = {
                    navHostController.navigate(Destination.Search.route)
                },
                onStarredClick = {
                    navHostController.navigate(Destination.StarredRepositoriesPage.route)
                }
            )
        }

        composable(
            route = Destination.RepositoryCard.routeWithArgs,
            arguments = Destination.RepositoryCard.arguments,
            deepLinks = listOf(
                navDeepLink { uriPattern = "mysuperapp://repo/{$ARG_CARD_ID}" }
            )
        ) { backStackEntry ->

            val cardId = backStackEntry.arguments!!.getInt(ARG_CARD_ID)

            val viewModel: RepositoryScreenViewModel =
                getViewModel(parameters = { parametersOf(cardId) })
            RepositoryScreen(
                viewModel = viewModel,
                onBackClick = { navHostController.popBackStack() }
            )
        }

        composable(Destination.StarredRepositoriesPage.route) {
            val viewModel: StarredRepositoriesViewModel = getViewModel()
            StarredRepositoriesScreen(
                viewModel = viewModel,
                onBackClick = { navHostController.popBackStack() },
                onRepoClick = { cardId ->
                    navHostController.navigate("${Destination.RepositoryCard.route}/$cardId")
                }
            )
        }

        composable(Destination.Search.route) {
            val vm: SearchViewModel = getViewModel()
            SearchScreen(
                viewModel = vm,
                onBack = { navHostController.popBackStack() },
                onRepoClick = { id ->
                    navHostController.navigate("${Destination.RepositoryCard.route}/$id")
                }
            )
        }

    }
}
