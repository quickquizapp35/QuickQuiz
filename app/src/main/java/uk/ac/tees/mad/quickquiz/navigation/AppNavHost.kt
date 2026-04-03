package uk.ac.tees.mad.quickquiz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import uk.ac.tees.mad.quickquiz.ui.authscreen.AuthScreen
import uk.ac.tees.mad.quickquiz.ui.home.HomeScreen
import uk.ac.tees.mad.quickquiz.ui.quiz.QuizScreen
import uk.ac.tees.mad.quickquiz.ui.result.ResultScreen
import uk.ac.tees.mad.quickquiz.ui.result.ResultUiState
import uk.ac.tees.mad.quickquiz.ui.setting.SettingsScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: NavRoutes
) {

    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        composable(NavRoutes.Home.route) {
            HomeScreen(
                onNavigateToQuiz = { id, difficulty ->
                    navController.navigate(NavRoutes.Quiz.quizRoute(id, difficulty))
                },
                onSettingClick = {
                    navController.navigate(NavRoutes.Setting.route)
                }
            )
        }

        composable(NavRoutes.Quiz.route,
            arguments = listOf(
                navArgument("categoryId") { type = NavType.IntType },
                navArgument("difficulty") { type = NavType.StringType }
            )

        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("categoryId")?:9
            val difficulty = backStackEntry.arguments?.getString("difficulty")?:"medium"

            QuizScreen(
                id = id,
                difficulty = difficulty,
                onNavigateToHome = {
                    navController.popBackStack()
                },
                onNavigateToResult = {
                    navController.navigate(NavRoutes.Result.route)
                }
            )
        }

        composable(NavRoutes.Auth.route) {
            AuthScreen(onNavigateToHome = {
                navController.navigate(NavRoutes.Home.route) {
                    popUpTo(NavRoutes.Auth.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(NavRoutes.Setting.route) {
            SettingsScreen(
                onNavBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(NavRoutes.Result.route) {
            ResultScreen(
                uiState = ResultUiState(
                    categoryName = "Mathematics",
                    totalQuestions = 10,
                    correctAnswers = 7
                ),
                onRetry = {},
                onBackToHome = {},
                onBack = {}
            )
        }
    }
}