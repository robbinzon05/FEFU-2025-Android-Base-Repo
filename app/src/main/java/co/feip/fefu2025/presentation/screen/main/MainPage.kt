package co.feip.fefu2025.presentation.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.R
import co.feip.fefu2025.domain.model.RepoCardModel
import co.feip.fefu2025.presentation.components.RepoCard
import co.feip.fefu2025.ui.theme.FEFU2025AndroidBaseRepoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPageContent(
    state: MainUIState,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
) {
    if (state.isLoading) {
        CircularProgressIndicator()
    } else {
        Scaffold(
            topBar = {
                SearchBar(
                    query = searchQuery,
                    onQueryChange = onSearchQueryChange,
                    onSearch = { onActiveChange(false) },
                    active = active,
                    onActiveChange = onActiveChange,
                    placeholder = { Text("Search repositories...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                ) {
                    if (searchQuery.isNotEmpty()) {
                        Text(
                            text = "Results for '$searchQuery'",
                            modifier = Modifier.padding(16.dp)
                        )
                    } else {
                        Text(
                            text = "Recently requests",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                LazyColumn(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Text(
                            text = "My stars",
                            fontWeight = FontWeight.Bold,
                            fontSize = 21.sp
                        )
                    }
                    item {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            items(state.starredRepositories) { cardInfo ->
                                RepoCard(cardInfo = cardInfo)
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
                        RepoCard(cardInfo = cardInfo)
                    }
                }
            }
        }
    }
}

@Composable
fun MainPage(viewModel: MainPageViewModel) {
    val state by viewModel.uiState.collectAsState()

    var searchQuery by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    MainPageContent(
        state = state,
        searchQuery = searchQuery,
        onSearchQueryChange = { searchQuery = it },
        active = active,
        onActiveChange = { active = it },
    )
}

@Preview(showBackground = true)
@Composable
fun MainPagePreview() {
    FEFU2025AndroidBaseRepoTheme {
        val previewState = MainUIState(
            starredRepositories = List<RepoCardModel>(10) {
                RepoCardModel(
                    name = "ExampleGitLab.org/GitLab Community",
                    description = "GitLab Community Edition (CE) is a" +
                            "n open source end-to-end software developm" +
                            "ent platform with built-in version control, " +
                            "issue tracking, code review, CI/CD, and more." +
                            " Self-host GitLab CE on your own servers, in a container, or on a cloud provider",
                    forks = 4774,
                    stars = 5407,
                    icon = R.drawable.ic_launcher_foreground
                )
            },
            allRepositories = List<RepoCardModel>(20) {
                RepoCardModel(
                    name = "ExampleGitLab.org/GitLab Community",
                    description = "GitLab Community Edition (CE) is a" +
                            "n open source end-to-end software developm" +
                            "ent platform with built-in version control, " +
                            "issue tracking, code review, CI/CD, and more." +
                            " Self-host GitLab CE on your own servers, in a container, or on a cloud provider",
                    forks = 4774,
                    stars = 5407,
                    icon = R.drawable.ic_launcher_foreground
                )
            },
            isLoading = false
        )
        MainPageContent(
            state = previewState,
            searchQuery = "",
            onSearchQueryChange = {},
            active = false,
            onActiveChange = {},
        )
    }
}
