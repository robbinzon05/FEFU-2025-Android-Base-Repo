package co.feip.fefu2025.presentation.screen.repo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.R
import co.feip.fefu2025.domain.model.RepositoryScreenModel
import co.feip.fefu2025.presentation.components.LanguageItemView
import co.feip.fefu2025.presentation.components.LanguageLine
import co.feip.fefu2025.presentation.components.MyFlexBoxLayout
import androidx.compose.ui.viewinterop.AndroidView
import co.feip.fefu2025.domain.model.LanguageModel

@Composable
fun RepositoryScreen(
    viewModel: RepositoryScreenViewModel,
    onBackClick: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val repo = state.repositoryScreenModel

    when {
        state.isLoading -> {
            CircularProgressIndicator()
        }

        else -> {
            if (repo != null) {
                RepositoryScreenContent(
                    repoScreen = repo,
                    onBackClick = onBackClick
                )
            }
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
                for (lang in languages) {
                    val itemView = LanguageItemView(context).apply {
                        setLanguageName(lang.name)
                        setCircleColor(lang.color)
                        setUsagePercent(lang.percent)
                    }
                    addView(itemView)
                }
            }
        },
        update = { view ->
            view.removeAllViews()

            languages.forEach { lang ->
                val itemView = LanguageItemView(view.context).apply {
                    setLanguageName(lang.name)
                    setCircleColor(lang.color)
                    setUsagePercent(lang.percent)
                }
                view.addView(itemView)
            }
        }
    )
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
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
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
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = modifier.size(60.dp),
                    painter = painterResource(repoScreen.icon),
                    contentDescription = ""
                )
                Column {
                    Text(
                        text = repoScreen.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = modifier.size(20.dp),
                            painter = painterResource(R.drawable.git_fork_svgrepo_com),
                            contentDescription = ""
                        )
                        Text(repoScreen.forks.toString())
                        Spacer(modifier = modifier.width(8.dp))
                        Icon(
                            modifier = modifier.size(18.dp),
                            painter = painterResource(R.drawable.star_svgrepo_com),
                            contentDescription = ""
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
            Text(
                text = repoScreen.description,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = "Languages:",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.W900
            )
            LanguageLine(
                modifier = modifier,
                languages = repoScreen.languages
            )
            FlexBoxLanguages(
                modifier = modifier,
                languages = repoScreen.languages
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun RepoScreenContentPreview() {
    val onBackClick = {}
    val fakeRepo = RepositoryScreenModel(
        name = "ExampleGitLab.org/GitLab Community",
        description = "GitLab Community Edition (CE) is a" +
                "n open source end-to-end software developm" +
                "ent platform with built-in version control, " +
                "issue tracking, code review, CI/CD, and more." +
                " Self-host GitLab CE on your own servers, in a container, or on a cloud provider",
        dataCreate = "2025-03-25",
        forks = 4774,
        stars = 5407,
        icon = R.drawable.ic_launcher_foreground,
        languages = listOf(
            LanguageModel("Kotlin", 70f, 0xFF7F52FF),
            LanguageModel("XML", 30f, 0xFFFFC107)
        )
    )
    RepositoryScreenContent(fakeRepo, onBackClick)
}
