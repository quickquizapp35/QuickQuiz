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

        }

        composable(NavRoutes.Result.route) {

        }
    }
}