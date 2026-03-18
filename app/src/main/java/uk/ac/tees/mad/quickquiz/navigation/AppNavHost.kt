package uk.ac.tees.mad.quickquiz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uk.ac.tees.mad.quickquiz.ui.authscreen.AuthScreen
import uk.ac.tees.mad.quickquiz.ui.home.HomeScreen

@Composable
fun AppNavHost(
    navController : NavHostController,
    startDestination : NavRoutes
){

    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ){
        composable(NavRoutes.Home.route){
            HomeScreen {
                //navigate to quiz screen
            }
        }

        composable(NavRoutes.Quiz.route){

        }

        composable(NavRoutes.Auth.route){
            AuthScreen(onNavigateToHome = {
                navController.navigate(NavRoutes.Home.route){
                    popUpTo(NavRoutes.Auth.route){
                        inclusive = true
                    }
                }
            })
        }

        composable(NavRoutes.Setting.route){

        }

        composable(NavRoutes.Result.route){

        }
    }
}