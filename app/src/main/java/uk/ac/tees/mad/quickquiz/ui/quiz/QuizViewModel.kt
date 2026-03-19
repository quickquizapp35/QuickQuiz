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
                             questionList = success.map { it.toUi() }
                         )
                     }
                 }
             )
        }
    }
}