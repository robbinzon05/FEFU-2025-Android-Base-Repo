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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import co.feip.fefu2025.R
import co.feip.fefu2025.domain.model.LanguageModel
import co.feip.fefu2025.domain.model.RepositoryScreenModel
import co.feip.fefu2025.presentation.components.LanguageItemView
import co.feip.fefu2025.presentation.components.LanguageLine
import co.feip.fefu2025.presentation.components.MyFlexBoxLayout
import co.feip.fefu2025.presentation.util.UiState


@Composable
fun RepositoryScreen(
    viewModel: RepositoryScreenViewModel,
    onBackClick: () -> Unit
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
            Button(onClick = viewModel::retry) {
                Text("Повторить")
            }
        }

        is UiState.Success -> {
            RepositoryScreenContent(
                repoScreen = state.data,
                onBackClick = onBackClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryScreenContent(
    repoScreen: RepositoryScreenModel,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = modifier.size(60.dp),
                    painter = painterResource(repoScreen.icon),
                    contentDescription = null
                )
                Column {
                    Text(repoScreen.name, style = MaterialTheme.typography.titleMedium)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = modifier.size(20.dp),
                            painter = painterResource(R.drawable.git_fork_svgrepo_com),
                            contentDescription = null
                        )
                        Text(repoScreen.forks.toString())
                        Spacer(modifier = modifier.width(8.dp))
                        Icon(
                            modifier = modifier.size(18.dp),
                            painter = painterResource(R.drawable.star_svgrepo_com),
                            contentDescription = null
                        )
                        Spacer(modifier = modifier.width(6.dp))
                        Text(repoScreen.stars.toString())
                        Spacer(modifier = modifier.width(20.dp))
                        Text(
                            text = repoScreen.dataCreate,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.W300,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }
            }
            Spacer(modifier = modifier.height(8.dp))

            Text(repoScreen.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = modifier.height(8.dp))

            Text(
                text = "Languages:",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.W900
            )
            LanguageLine(languages = repoScreen.languages)

            FlexBoxLanguages(languages = repoScreen.languages)
        }
    }
}

@Composable
fun FlexBoxLanguages(
    modifier: Modifier = Modifier,
    languages: List<LanguageModel>
) {
    AndroidView(
        modifier = modifier.fillMaxWidth(),
        factory = { context ->
            MyFlexBoxLayout(context).apply {
                languages.forEach { lang ->
                    addView(
                        LanguageItemView(context).apply {
                            setLanguageName(lang.name)
                            setCircleColor(lang.color)
                            setUsagePercent(lang.percent)
                        }
                    )
                }
            }
        },
        update = { view ->
            view.removeAllViews()
            languages.forEach { lang ->
                view.addView(
                    LanguageItemView(view.context).apply {
                        setLanguageName(lang.name)
                        setCircleColor(lang.color)
                        setUsagePercent(lang.percent)
                    }
                )
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
private fun RepoScreenContentPreview() {
    val fakeRepo = RepositoryScreenModel(
        name = "ExampleGitLab.org/GitLab Community",
        description = "GitLab Community Edition (CE) …",
        dataCreate = "2025-03-25",
        forks = 4774,
        stars = 5407,
        icon = R.drawable.ic_launcher_foreground,
        languages = listOf(
            LanguageModel("Kotlin", 70f, 0xFF7F52FF),
            LanguageModel("XML", 30f, 0xFFFFC107)
        )
    )
    RepositoryScreenContent(repoScreen = fakeRepo, onBackClick = {})
}
