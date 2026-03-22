package uk.ac.tees.mad.quickquiz.navigation

import uk.ac.tees.mad.quickquiz.utils.QuizDifficulty

sealed class NavRoutes(val route : String) {
    object Home: NavRoutes("home")
    object Setting : NavRoutes("setting")
    object Auth : NavRoutes("Auth")
    object Quiz : NavRoutes("quiz/{categoryId}/{difficulty}"){
        fun quizRoute(categoryId: Int, difficulty: QuizDifficulty) =
            "quiz/$categoryId/${difficulty.apiPath}"
    }
    object Result : NavRoutes("result")
}