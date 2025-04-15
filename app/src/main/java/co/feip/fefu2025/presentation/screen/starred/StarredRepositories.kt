package co.feip.fefu2025.presentation.screen.starred

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.R
import co.feip.fefu2025.domain.model.RepositoryCardModel
import co.feip.fefu2025.presentation.components.RepositoryCard

@Composable
fun StarredRepositoriesScreen(
    viewModel: StarredRepositoriesViewModel,
    onBackClick: () -> Unit,
    onRepoClick: (Int) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    StarredRepositoriesContent(
        state = state,
        onRepoClick = onRepoClick,
        onBackClick = onBackClick,
        modifier = Modifier
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StarredRepositoriesContent(
    state: StarredRepositoriesUiState,
    onBackClick: () -> Unit,
    onRepoClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    if (state.isLoading) {
        CircularProgressIndicator()
    }
    else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "My Stars") },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = modifier
                    .padding(innerPadding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    LazyColumn(
                        modifier = modifier,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(state.starredRepositories) { cardInfo ->
                            RepositoryCard(
                                cardInfo = cardInfo,
                                onClick = { onRepoClick(cardInfo.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
private fun StarredRepositoriesPreview() {
    val fakeState = StarredRepositoriesUiState(
        starredRepositories = List(5) { index ->
            RepositoryCardModel(
                id = index,
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
    )

    val onRepoClick: (Int) -> Unit = { id -> println("Clicked repo with id = $id") }
    val onBackClick = {}

    StarredRepositoriesContent(
        state = fakeState,
        onRepoClick = onRepoClick,
        onBackClick = onBackClick,
        modifier = Modifier
    )
}