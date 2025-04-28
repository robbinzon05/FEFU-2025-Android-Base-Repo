/*
 *  StarredRepositories.kt
 *  ──────────────────────
 *  Экран «Избранные репозитории».
 *  Использует UiState вместо старого StarredRepositoriesUiState.
 */

package co.feip.fefu2025.presentation.screen.starred

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
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
import co.feip.fefu2025.presentation.util.UiState
import co.feip.fefu2025.ui.theme.FEFU2025AndroidBaseRepoTheme


@Composable
fun StarredRepositoriesScreen(
    viewModel: StarredRepositoriesViewModel,
    onBackClick: () -> Unit,
    onRepoClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {

        UiState.Loading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) { CircularProgressIndicator() }

        is UiState.Error -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = viewModel::retry) { Text("Повторить") }
        }

        is UiState.Success -> StarredRepositoriesContent(
            repos = state.data,
            onBackClick = onBackClick,
            onRepoClick = onRepoClick
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StarredRepositoriesContent(
    repos: List<RepositoryCardModel>,
    onBackClick: () -> Unit,
    onRepoClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "My Stars") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        if (repos.isEmpty()) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("У вас пока нет избранных репозиториев")
            }
        } else {
            LazyColumn(
                modifier = modifier
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(repos) { cardInfo ->
                    RepositoryCard(
                        cardInfo = cardInfo,
                        onClick = { onRepoClick(cardInfo.id) }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun StarredRepositoriesPreview() {
    val fakeRepos = List(3) { index ->
        RepositoryCardModel(
            id = index,
            name = "Repo $index",
            description = "Description $index",
            forks = 42,
            stars = 99,
            icon = R.drawable.ic_launcher_foreground
        )
    }
    FEFU2025AndroidBaseRepoTheme {
        StarredRepositoriesContent(
            repos = fakeRepos,
            onBackClick = {},
            onRepoClick = {}
        )
    }
}
