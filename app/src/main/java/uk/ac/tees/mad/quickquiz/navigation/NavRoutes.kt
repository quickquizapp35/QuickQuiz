package uk.ac.tees.mad.quickquiz.navigation

import uk.ac.tees.mad.quickquiz.utils.QuizDifficulty

sealed class NavRoutes(val route: String) {

    object Home : NavRoutes("home")
    object Setting : NavRoutes("setting")
    object Auth : NavRoutes("auth")

    object QuizGraph : NavRoutes("quiz_graph/{categoryId}/{difficulty}") {

        fun createRoute(
            categoryId: Int,
            difficulty: QuizDifficulty
        ) = "quiz_graph/$categoryId/${difficulty.apiPath}"
    }

    object Quiz : NavRoutes("quiz")
    object Result : NavRoutes("result")
}
