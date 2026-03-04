package uk.ac.tees.mad.quickquiz.navigation

sealed class NavRoutes(val route : String) {
    object Home: NavRoutes("home")
    object Setting : NavRoutes("setting")
    object Login : NavRoutes("login")
    object Signup : NavRoutes("signup")
    object Quiz : NavRoutes("quiz")
    object Result : NavRoutes("result")
}