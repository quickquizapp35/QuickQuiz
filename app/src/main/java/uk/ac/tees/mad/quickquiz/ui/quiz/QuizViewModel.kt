package uk.ac.tees.mad.quickquiz.ui.quiz

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.quickquiz.QuickQuizApp
import uk.ac.tees.mad.quickquiz.domain.repository.QuizRepository
import uk.ac.tees.mad.quickquiz.utils.NetworkError
import uk.ac.tees.mad.quickquiz.utils.toUi

class QuizViewModel(application: Application)
    : AndroidViewModel(application){
        private val quizRepository : QuizRepository =
        (application as QuickQuizApp).quizRepository


    private val _quizUiState = MutableStateFlow(QuizUiState())
    val quizUiState = _quizUiState.asStateFlow()
    fun fetchQuestion(id :Int, difficulty: String){

        viewModelScope.launch {
            _quizUiState.update {
                it.copy(isLoading = true)
            }

            val result = quizRepository.getQuizQuestions(id, difficulty)
             result.fold(
                 onFailure = {failure->
                     _quizUiState.update {
                         it.copy(
                             isLoading = false,
                             error =failure.NetworkError().message
                         )
                     }
                 },
                 onSuccess = {success->
                     _quizUiState.update { uiState ->
                         uiState.copy(
                             isLoading = false,
                             error = null,
                             questions = success.map { it.toUi() }
                         )
                     }
                 }
             )
        }
    }


    fun onOptionSelected(optionId: String) {
        _quizUiState.update {
            it.copy(selectedOptionId = optionId)
        }
    }


    fun onConfirmAnswer() {
        val state = _quizUiState.value
        val question = state.currentQuestion ?: return

        val selected = question.options
            .firstOrNull { it.id == state.selectedOptionId }
            ?: return

        val newScore =
            if (selected.isCorrect) state.score + 1
            else state.score

        val isLast = state.currentIndex == state.questions.lastIndex

        _quizUiState.update {
            it.copy(
                score = newScore,
                currentIndex = if (isLast) it.currentIndex else it.currentIndex + 1,
                selectedOptionId = null,
                isFinished = isLast
            )
        }
    }
}

