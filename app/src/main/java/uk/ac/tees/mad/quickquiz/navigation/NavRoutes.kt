package uk.ac.tees.mad.quickquiz.navigation

sealed class NavRoutes(val route : String) {
    object Home: NavRoutes("home")
    object Setting : NavRoutes("setting")
    object Auth : NavRoutes("Auth")
    object Quiz : NavRoutes("quiz")
    object Result : NavRoutes("result")
}