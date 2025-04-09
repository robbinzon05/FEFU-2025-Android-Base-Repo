package co.feip.fefu2025

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import co.feip.fefu2025.ui.theme.FEFU2025AndroidBaseRepoTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

data class LanguageData(
    val name: String,
    val percent: Float,
    val color: Long
)

@Composable
fun FlexBoxLanguages(
    modifier: Modifier = Modifier,
    languages: List<LanguageData>
) {
    AndroidView(
        modifier = modifier.fillMaxWidth(),
        factory = { context ->
            MyFlexBoxLayout(context).apply {
                for (lang in languages) {
                    val circleColor = lang.color
                    val usagePercent = lang.percent
                    val itemView = LanguageItemView(context).apply {
                        setLanguageName(lang.name)
                        setCircleColor(circleColor)
                        setUsagePercent(usagePercent)
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

@Composable
fun RepositoryScreen(
    cardInfo: CardInfo,
    dataCreate: String,
    languages: List<LanguageData>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = modifier.size(60.dp),
                painter = painterResource(cardInfo.icon),
                contentDescription = null
            )
            Column {
                Text(
                    text = cardInfo.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = modifier.size(20.dp),
                        painter = painterResource(R.drawable.git_fork_svgrepo_com),
                        contentDescription = null
                    )
                    Text(cardInfo.forks.toString())
                    Spacer(modifier = modifier.width(8.dp))
                    Icon(
                        modifier = modifier.size(18.dp),
                        painter = painterResource(R.drawable.star_svgrepo_com),
                        contentDescription = null
                    )
                    Spacer(modifier = modifier.width(6.dp))
                    Text(cardInfo.stars.toString())
                    Spacer(modifier = modifier.width(20.dp))
                    Text(
                        text = dataCreate,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.W300,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = cardInfo.description,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = stringResource(R.string.title_flexbox_reposcreen),
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.W900,
            fontSize = 15.sp
        )
        LanguageLine(
            modifier = modifier,
            languages = languages
        )
        FlexBoxLanguages(
            modifier = modifier,
            languages = languages
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewRepositoryScreen() {
    FEFU2025AndroidBaseRepoTheme {
        RepositoryScreen(
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
            ),
            dataCreate = "2025-03-25",
            languages = listOf<LanguageData>(
                LanguageData("Python", 57f, 0xFF3572A5),
                LanguageData("Rust", 30f, 0xFFDEA584),
                LanguageData("C++", 13f, 0xFFF34B7D)
            )
        )
    }
}
