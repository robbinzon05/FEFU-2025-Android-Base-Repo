package co.feip.fefu2025.presentation.screen.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import co.feip.fefu2025.presentation.components.RepositoryCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(
    viewModel: MainPageViewModel,
    navToSearch: () -> Unit,
    onRepoClick: (Int) -> Unit,
    onStarredClick: () -> Unit
) {
    val allItems  = viewModel.allProjects.collectAsLazyPagingItems()
    val starItems = viewModel.starred.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("GitLab Projects") },
                actions = {
                    IconButton(onClick = navToSearch) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            item {
                Text(
                    "My stars",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = onStarredClick)
                )
            }

            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(count = minOf(starItems.itemCount, 10)) { index ->
                        starItems[index]?.let {
                            RepositoryCard(
                                cardInfo = it,
                                onClick = { onRepoClick(it.id) }
                            )
                        }
                    }

                    when (val st = starItems.loadState.refresh) {
                        is androidx.paging.LoadState.Loading ->
                            item { CircularProgressIndicator(modifier = Modifier.padding(8.dp)) }
                        is androidx.paging.LoadState.Error ->
                            item { Button(onClick = starItems::retry) { Text("Retry") } }
                        else -> {}
                    }
                }
            }

            item { Text("All projects", style = MaterialTheme.typography.titleMedium) }

            items(count = allItems.itemCount) { index ->
                allItems[index]?.let {
                    RepositoryCard(
                        cardInfo = it,
                        onClick = { onRepoClick(it.id) }
                    )
                }
            }

            allItems.apply {
                when (val st = loadState.append) {
                    is androidx.paging.LoadState.Loading ->
                        item {
                            Box(Modifier.fillMaxWidth(), Alignment.Center) {
                                CircularProgressIndicator()
                            }
                        }
                    is androidx.paging.LoadState.Error ->
                        item {
                            Box(Modifier.fillMaxWidth(), Alignment.Center) {
                                Button(onClick = ::retry) { Text("Retry") }
                            }
                        }
                    else -> {}
                }
            }
        }
    }
}
