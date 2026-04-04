package uk.ac.tees.mad.quickquiz.ui.quiz

import uk.ac.tees.mad.quickquiz.ui.quiz.component.model.QuizQuestionUi
import uk.ac.tees.mad.quickquiz.utils.QuizDifficulty

data class QuizUiState(
    val isLoading: Boolean = false,
    val error: String? = null,

    val questions: List<QuizQuestionUi> = emptyList(),
    val currentIndex: Int = 0,

    val selectedOptionId: String? = null,

    val score: Int = 0,
    val isFinished: Boolean = false,

    val difficulty: String = QuizDifficulty.MEDIUM.apiPath,
    val categoryId: Int = 9,
    val categoryName: String = "General Knowledge"
) {

    val currentQuestion: QuizQuestionUi?
        get() = questions.getOrNull(currentIndex)

    val progress: Float
        get() = if (questions.isEmpty()) 0f
        else (currentIndex + 1) / questions.size.toFloat()

    val percentageCorrect :Float
        get() = if (questions.isEmpty()) 0f
        else score / questions.size.toFloat()


    val canConfirm: Boolean
        get() = selectedOptionId != null
    val totaQuestion :Int
        get () = questions.size
    val incorrect :Int
        get() = totaQuestion - score

}
