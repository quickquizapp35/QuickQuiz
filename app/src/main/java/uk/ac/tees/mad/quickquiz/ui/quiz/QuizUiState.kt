package uk.ac.tees.mad.quickquiz.ui.quiz

import uk.ac.tees.mad.quickquiz.domain.model.QuizQuestion
import uk.ac.tees.mad.quickquiz.ui.quiz.component.model.QuizQuestionUi

data class QuizUiState(
    val questionList :List<QuizQuestionUi> = emptyList(),
    val isLoading : Boolean = false,
    val error: String ? = null
)