package co.feip.fefu2025.presentation.components
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.R
import co.feip.fefu2025.domain.model.RepositoryCardModel
import co.feip.fefu2025.ui.theme.FEFU2025AndroidBaseRepoTheme

@Composable
fun RepositoryCard(cardInfo: RepositoryCardModel, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(modifier = modifier
            .padding(16.dp)
            .width(300.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = modifier.size(60.dp),
                    painter = painterResource(cardInfo.icon),
                    contentDescription = null
                )
                Spacer(modifier = modifier.width(8.dp))
                Column {
                    Text(
                        text = cardInfo.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            modifier = modifier.size(20.dp),
                            painter = painterResource(id = R.drawable.git_fork_svgrepo_com),
                            contentDescription = null
                        )
                        Text(
                            modifier = modifier.padding(start = 4.dp),
                            text = cardInfo.forks.toString()
                        )
                        Spacer(modifier = modifier.width(8.dp))
                        Icon(
                            modifier = modifier.size(20.dp),
                            painter = painterResource(id = R.drawable.star_svgrepo_com),
                            contentDescription = null
                        )
                        Text(
                            modifier = modifier.padding(start = 4.dp),
                            text = cardInfo.stars.toString()
                        )
                    }
                }
            }
            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = cardInfo.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewRepositoryCard() {
    FEFU2025AndroidBaseRepoTheme {
        RepositoryCard(
            cardInfo = RepositoryCardModel(
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
        )
    }
}