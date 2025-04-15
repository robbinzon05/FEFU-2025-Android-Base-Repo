package co.feip.fefu2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import co.feip.fefu2025.navigation.AppNavHost
import co.feip.fefu2025.ui.theme.FEFU2025AndroidBaseRepoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FEFU2025AndroidBaseRepoTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController = rememberNavController()
                    AppNavHost(
                        navHostController = navController,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}
