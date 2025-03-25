package co.feip.fefu2025
import androidx.compose.foundation.layout.Box
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
import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxWidth
import kotlin.random.Random

val languages = listOf(
    "C++",
    "Python",
    "Ruby",
    "JavaScript",
    "Kotlin",
    "C",
    "Haskell",
    "Swift",
    "Rust"
)

@Composable
fun RepoScreen(cardInfo: CardInfo) {
    Box(modifier = cardInfo.modifier) {
        Column(modifier = cardInfo.modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(cardInfo.icon),
                    contentDescription = "",
                    modifier = Modifier.size(60.dp)
                )
                Column {
                    Text(
                        text = cardInfo.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(R.drawable.git_fork_svgrepo_com),
                            contentDescription = "",
                            modifier = Modifier.size(20.dp)
                        )
                        Text(cardInfo.forks.toString())
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(R.drawable.star_svgrepo_com),
                            contentDescription = "",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(cardInfo.stars.toString())
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(
                            text = "2025-03-25",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.W300,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = cardInfo.description,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Languages:",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.W300,
                fontStyle = FontStyle.Italic
            )
            AndroidView(
                factory = { context ->
                    MyFlexBoxLayout(context).apply {
                        for (lang in languages) {
                            val circleColor = getRandomColor()
                            val usagePercent = Random.nextDouble(0.0, 100.0).toFloat()
                            val itemView = LanguageItemView(context).apply {
                                setLanguageName(lang)
                                setCircleColor(circleColor)
                                setUsagePercent(usagePercent)
                            }
                            addView(itemView)
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

fun getRandomColor(): Int {
    val red = (0..255).random()
    val green = (0..255).random()
    val blue = (0..255).random()

    return Color.rgb(red,green, blue)
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    FEFU2025AndroidBaseRepoTheme {
        RepoScreen(
            cardInfo = CardInfo(
                name = "ExampleGitLab.org/GitLab Community",
                description = "GitLab Community Edition (CE) is a" +
                        "n open source end-to-end software developm" +
                        "ent platform with built-in version control, " +
                        "issue tracking, code review, CI/CD, and more." +
                        " Self-host GitLab CE on your own servers, in a container, or on a cloud provider",
                forks = 4774,
                stars= 5407,
                icon = R.drawable.ic_launcher_foreground,
                modifier = Modifier
            )
        )
    }
}