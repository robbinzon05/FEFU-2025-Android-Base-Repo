package co.feip.fefu2025.presentation.screen.repo

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.R
import co.feip.fefu2025.presentation.util.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryScreen(
    viewModel: RepositoryScreenViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    when (val st = uiState) {
        UiState.Loading -> Box(Modifier.fillMaxSize(), Alignment.Center) {
            CircularProgressIndicator()
        }

        is UiState.Error -> Box(Modifier.fillMaxSize(), Alignment.Center) {
            Button(onClick = viewModel::retry) { Text("Retry") }
        }

        is UiState.Success -> {
            val repo = st.data
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {},
                        navigationIcon = {
                            IconButton(onClick = onBackClick) {
                                Icon(Icons.Default.ArrowBack, null)
                            }
                        }
                    )
                }
            ) { pad ->
                Column(
                    Modifier
                        .padding(pad)
                        .padding(16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(R.drawable.ic_launcher_foreground),
                            contentDescription = null,
                            modifier = Modifier.size(60.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(repo.name, style = MaterialTheme.typography.titleMedium)
                            Spacer(Modifier.height(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painterResource(R.drawable.git_fork_svgrepo_com),
                                    null,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(Modifier.width(4.dp))
                                Text(repo.forks.toString())
                                Spacer(Modifier.width(12.dp))
                                Icon(
                                    painterResource(R.drawable.star_svgrepo_com),
                                    null,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(Modifier.width(4.dp))
                                Text(repo.stars.toString())
                            }
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                    Text(repo.description, style = MaterialTheme.typography.bodyMedium)
                    Spacer(Modifier.height(12.dp))
                    Text(
                        "Created: ${repo.dataCreate}",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.W300
                    )
                }
            }
        }
    }
}
