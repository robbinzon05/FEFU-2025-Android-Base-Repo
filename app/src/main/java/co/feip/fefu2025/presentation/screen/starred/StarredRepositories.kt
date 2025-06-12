package co.feip.fefu2025.presentation.screen.starred

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import co.feip.fefu2025.presentation.components.RepositoryCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StarredRepositoriesScreen(
    viewModel: StarredRepositoriesViewModel,
    onBack: () -> Unit,
    onRepoClick: (Int) -> Unit
) {
    val items = viewModel.items.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Stars") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                }
            )
        }
    ) { pad ->
        LazyColumn(
            modifier = Modifier
                .padding(pad)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(count = items.itemCount) { index ->
                val card = items[index]
                if (card != null) {
                    RepositoryCard(
                        cardInfo = card,
                        onClick = { onRepoClick(card.id) }
                    )
                }
            }

            when (val st = items.loadState.append) {
                is androidx.paging.LoadState.Loading ->
                    item {
                        Box(Modifier.fillMaxWidth(), Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }

                is androidx.paging.LoadState.Error ->
                    item {
                        Box(Modifier.fillMaxWidth(), Alignment.Center) {
                            Button(onClick = items::retry) {
                                Text("Retry")
                            }
                        }
                    }

                else -> {}
            }
        }
    }
}
