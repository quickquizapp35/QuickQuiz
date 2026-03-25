package uk.ac.tees.mad.quickquiz.ui.result

data class ResultUiState(
    val categoryName: String,
    val totalQuestions: Int,
    val correctAnswers: Int
) {
    val incorrectAnswers: Int
        get() = totalQuestions - correctAnswers

    val scoreRatio: Float
        get() = correctAnswers / totalQuestions.toFloat()
}
