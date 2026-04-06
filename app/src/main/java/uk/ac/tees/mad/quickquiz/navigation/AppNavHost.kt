package uk.ac.tees.mad.quickquiz.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import uk.ac.tees.mad.quickquiz.ui.authscreen.AuthScreen
import uk.ac.tees.mad.quickquiz.ui.home.HomeScreen
import uk.ac.tees.mad.quickquiz.ui.quiz.QuizScreen
import uk.ac.tees.mad.quickquiz.ui.quiz.QuizViewModel
import uk.ac.tees.mad.quickquiz.ui.result.ResultScreen
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
//                    navController.navigate(NavRoutes.Quiz.quizRoute(id, difficulty))
                    navController.navigate(NavRoutes.QuizGraph.createRoute(id, difficulty))
                },
                onSettingClick = {
                    navController.navigate(NavRoutes.Setting.route)
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
                },
                onLogoutClick = {
                    navController.navigate(NavRoutes.Auth.route){
                        popUpTo(NavRoutes.Setting.route){
                            inclusive = true
                        }
                    }
                }
            )
        }

        navigation(
            route = NavRoutes.QuizGraph.route,
            startDestination = NavRoutes.Quiz.route,
            arguments = listOf(
                navArgument("categoryId") {
                    type = NavType.IntType
                },
                navArgument("difficulty") {
                    type = NavType.StringType
                }
            )
        ) {

            composable(NavRoutes.Quiz.route) { backStackEntry ->

                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(NavRoutes.QuizGraph.route)
                }

                val viewModel: QuizViewModel =
                    viewModel(parentEntry)

                val categoryId =
                    parentEntry.arguments?.getInt("categoryId") ?: 9

                val difficulty =
                    parentEntry.arguments?.getString("difficulty") ?: "medium"


                QuizScreen(
                    id = categoryId,
                    difficulty = difficulty,
                    viewModel = viewModel,
                    onNavigateToHome = {
                        navController.popBackStack()
                    },
                    onNavigateToResult = {
                        navController.navigate(NavRoutes.Result.route)
                    }
                )
            }

            composable(NavRoutes.Result.route) { backStackEntry ->

                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(NavRoutes.QuizGraph.route)
                }

                val viewModel: QuizViewModel =
                    viewModel(parentEntry)

                ResultScreen(
                    viewModel = viewModel,
                    onRetrySame = {
                        viewModel.retrySameQuiz()
                        navController.popBackStack()
                    },
                    onRetryNew = {
                          viewModel.retryNewQuiz()
                        navController.popBackStack()
                    },
                    onBackToHome = {
                        navController.navigate(NavRoutes.Home.route) {
                            popUpTo(NavRoutes.Home.route) {
                                inclusive = true
                            }
                        }
                    },
                    onNavBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}