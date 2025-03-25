package co.feip.fefu2025
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
import co.feip.fefu2025.ui.theme.FEFU2025AndroidBaseRepoTheme

data class CardInfo (
    val name: String,
    val description: String,
    val forks: Int,
    val stars: Int,
    val icon: Int,
    val modifier: Modifier
)

@Composable
fun RepoCard(cardInfo: CardInfo) {
    Card(modifier = cardInfo.modifier) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(cardInfo.icon),
                    contentDescription = "Repository icon",
                    modifier = Modifier.size(60.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
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
                            painter = painterResource(id = R.drawable.git_fork_svgrepo_com),
                            contentDescription = "Forks",
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = cardInfo.forks.toString(),
                            modifier = Modifier.padding(start = 4.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.star_svgrepo_com),
                            contentDescription = "Stars",
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = cardInfo.stars.toString(),
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = cardInfo.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCard() {
    FEFU2025AndroidBaseRepoTheme {
        RepoCard(
            cardInfo = CardInfo(
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
        )
    }
}