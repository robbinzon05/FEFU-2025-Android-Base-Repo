package co.feip.fefu2025
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import co.feip.fefu2025.presentation.screen.main.MainPage
import co.feip.fefu2025.presentation.screen.main.MainPageViewModel
import co.feip.fefu2025.ui.theme.FEFU2025AndroidBaseRepoTheme
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FEFU2025AndroidBaseRepoTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val viewModel : MainPageViewModel = getViewModel()
                    MainPage(viewModel = viewModel)
                }
            }
        }
    }
}
