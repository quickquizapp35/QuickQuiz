package uk.ac.tees.mad.quickquiz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(
    navController : NavHostController,
    startDestination : String
){

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(NavRoutes.Home.route){

        }

        composable(NavRoutes.Quiz.route){

        }

        composable(NavRoutes.Signup.route){

        }

        composable(NavRoutes.Login.route){

        }

        composable(NavRoutes.Setting.route){

        }

        composable(NavRoutes.Result.route){

        }
    }
}