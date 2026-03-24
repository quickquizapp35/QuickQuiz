package uk.ac.tees.mad.quickquiz.ui.quiz

import uk.ac.tees.mad.quickquiz.domain.model.QuizQuestion
import uk.ac.tees.mad.quickquiz.ui.quiz.component.model.QuizQuestionUi

//data class QuizUiState(
//    val questionList :List<QuizQuestionUi> = emptyList(),
//    val isLoading : Boolean = false,
//    val error: String ? = null
//)

data class QuizUiState(
    val isLoading: Boolean = false,
    val error: String? = null,

    val questions: List<QuizQuestionUi> = emptyList(),
    val currentIndex: Int = 0,

    val selectedOptionId: String? = null,

    val score: Int = 0,
    val isFinished: Boolean = false
) {

    val currentQuestion: QuizQuestionUi?
        get() = questions.getOrNull(currentIndex)

    val progress: Float
        get() = if (questions.isEmpty()) 0f
        else (currentIndex + 1) / questions.size.toFloat()

    val canConfirm: Boolean
        get() = selectedOptionId != null
    val totaQuestion :Int
        get () = questions.size
}
