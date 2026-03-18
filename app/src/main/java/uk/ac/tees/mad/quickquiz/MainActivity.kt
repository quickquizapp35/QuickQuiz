package uk.ac.tees.mad.quickquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.quickquiz.navigation.AppNavHost
import uk.ac.tees.mad.quickquiz.navigation.NavRoutes
import uk.ac.tees.mad.quickquiz.ui.splash.SplashUiState
import uk.ac.tees.mad.quickquiz.ui.splash.SplashViewModel
import uk.ac.tees.mad.quickquiz.ui.theme.QuikQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val splashViewModel: SplashViewModel by viewModels()

        splashScreen.setKeepOnScreenCondition {
            splashViewModel.splashUiState.value is SplashUiState.Loading
        }
        setContent {
            val navController = rememberNavController()
            val startDestination = when(splashViewModel
                .splashUiState
                .collectAsState()
                .value){
                SplashUiState.Loading -> null
                SplashUiState.NavigateToAuth -> NavRoutes.Auth
                SplashUiState.NavigateToHome -> NavRoutes.Home
            }

            if(startDestination != null) {
                QuikQuizTheme {
                    AppNavHost(
                        navController = navController,
                        startDestination = startDestination
                    )
                }
            }
        }
    }
}