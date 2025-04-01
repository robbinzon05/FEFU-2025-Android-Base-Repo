package co.feip.fefu2025
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.ui.theme.FEFU2025AndroidBaseRepoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(
    myStarsRepoCards: List<CardInfo>,
    allProjectsRepoCards: List<CardInfo>,
    modifier: Modifier = Modifier
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            SearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = { active = false },
                active = active,
                onActiveChange = { active = it },
                placeholder = { Text("Search repositories...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
            ) {
                if (searchQuery.isNotEmpty()) {
                    Text(
                        text = "Results for '$searchQuery'",
                        modifier = modifier.padding(16.dp)
                    )
                } else {
                    Text(
                        text = "Recently requests",
                        modifier = modifier.padding(16.dp)
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(modifier = modifier.padding(innerPadding)) {
            LazyColumn(
                modifier = modifier.padding(12.dp),
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
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ){
                        items(myStarsRepoCards) { cardInfo ->
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
                items(allProjectsRepoCards) { cardInfo ->
                    RepoCard(cardInfo = cardInfo)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPagePreview() {
    val myStarsRepoCards = List<CardInfo>(10) {
        CardInfo(
            name = "ExampleGitLab.org/GitLab Community",
            description = "GitLab Community Edition (CE) is a" +
                    "n open source end-to-end software developm" +
                    "ent platform with built-in version control, " +
                    "issue tracking, code review, CI/CD, and more." +
                    " Self-host GitLab CE on your own servers, in a container, or on a cloud provider",
            forks = 4774,
            stars = 5407,
            icon = R.drawable.ic_launcher_foreground,
            modifier = Modifier
        )
    }
    val allProjectsRepoCards = List<CardInfo>(20) {
        CardInfo(
            name = "ExampleGitLab.org/GitLab Community",
            description = "GitLab Community Edition (CE) is a" +
                    "n open source end-to-end software developm" +
                    "ent platform with built-in version control, " +
                    "issue tracking, code review, CI/CD, and more." +
                    " Self-host GitLab CE on your own servers, in a container, or on a cloud provider",
            forks = 4774,
            stars = 5407,
            icon = R.drawable.ic_launcher_foreground,
            modifier = Modifier
        )
    }
    FEFU2025AndroidBaseRepoTheme {
        MainPage(myStarsRepoCards, allProjectsRepoCards, modifier = Modifier)
    }
}
