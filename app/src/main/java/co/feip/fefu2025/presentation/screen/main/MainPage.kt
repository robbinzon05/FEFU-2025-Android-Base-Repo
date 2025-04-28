package co.feip.fefu2025.presentation.screen.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.R
import co.feip.fefu2025.domain.model.RepositoryCardModel
import co.feip.fefu2025.presentation.components.RepositoryCard
import co.feip.fefu2025.presentation.util.UiState
import co.feip.fefu2025.ui.theme.FEFU2025AndroidBaseRepoTheme


data class MainUIState(
    val starredRepositories: List<RepositoryCardModel> = emptyList(),
    val allRepositories:     List<RepositoryCardModel> = emptyList(),
    val isLoading: Boolean = false
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPageContent(
    modifier: Modifier = Modifier,
    state: MainUIState,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    onRepoClick: (Int) -> Unit,
    onStarredClick: () -> Unit
) {
    Scaffold(
        topBar = {
            SearchBar(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                query = searchQuery,
                onQueryChange = onSearchQueryChange,
                onSearch = { onActiveChange(false) },
                active = active,
                onActiveChange = onActiveChange,
                placeholder = { Text("Search repositories…") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            ) {
                if (searchQuery.isNotEmpty()) {
                    Text(
                        modifier = modifier.padding(16.dp),
                        text = "Results for ‘$searchQuery’"
                    )
                } else {
                    Text(
                        modifier = modifier.padding(16.dp),
                        text = "Recently requests"
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text(
                        modifier = modifier.clickable { onStarredClick() },
                        text = "My stars",
                        fontWeight = FontWeight.Bold,
                        fontSize = 21.sp
                    )
                }
                item {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(state.starredRepositories) { cardInfo ->
                            RepositoryCard(
                                cardInfo = cardInfo,
                                onClick = { onRepoClick(cardInfo.id) }
                            )
                        }
                    }
                }
                item {
                    Text(
                        text = "All projects",
                        fontWeight = FontWeight.Bold,
                        fontSize = 21.sp
                    )
                }
                items(state.allRepositories) { cardInfo ->
                    RepositoryCard(
                        cardInfo = cardInfo,
                        onClick = { onRepoClick(cardInfo.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun MainPage(
    viewModel: MainPageViewModel,
    navToSearch: () -> Unit,
    onRepoClick: (Int) -> Unit,
    onStarredClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        UiState.Loading -> Box(Modifier.fillMaxSize(), Alignment.Center) {
            CircularProgressIndicator()
        }

        is UiState.Error -> Box(Modifier.fillMaxSize(), Alignment.Center) {
            Button(onClick = viewModel::retry) { Text("Повторить") }
        }

        is UiState.Success -> {
            val data = state.data
            var searchQuery by rememberSaveable { mutableStateOf("") }

            MainPageContent(
                modifier = Modifier,
                state = MainUIState(
                    starredRepositories = data.starred,
                    allRepositories = data.all,
                    isLoading = false
                ),
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it },
                active = false,
                onActiveChange = { if (it) navToSearch() },
                onRepoClick = onRepoClick,
                onStarredClick = onStarredClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainPagePreview() {
    val onRepoClick: (Int) -> Unit = {}
    val onStarredClick = {}

    FEFU2025AndroidBaseRepoTheme {
        val previewState = MainUIState(
            starredRepositories = List(3) { index ->
                RepositoryCardModel(
                    id = index,
                    name = "Repo $index",
                    description = "Description $index",
                    forks = 40,
                    stars = 100,
                    icon = R.drawable.ic_launcher_foreground
                )
            },
            allRepositories = List(5) { index ->
                RepositoryCardModel(
                    id = index,
                    name = "Repo $index",
                    description = "Description $index",
                    forks = 40,
                    stars = 100,
                    icon = R.drawable.ic_launcher_foreground
                )
            },
            isLoading = false
        )

        MainPageContent(
            modifier = Modifier,
            state = previewState,
            searchQuery = "",
            onSearchQueryChange = {},
            active = false,
            onActiveChange = {},
            onRepoClick = onRepoClick,
            onStarredClick = onStarredClick
        )
    }
}
