package co.feip.fefu2025
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import co.feip.fefu2025.ui.theme.FEFU2025AndroidBaseRepoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FEFU2025AndroidBaseRepoTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                ) {
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
        }
    }
}
