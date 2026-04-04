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

        _quizUiState.update {
            it.copy(
                categoryId = id,
                difficulty = difficulty,
                categoryName = CategoryTextMapper.getCategoryName(id)
            )
        }

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

    fun retrySameQuiz() {
        _quizUiState.update {
            it.copy(
                currentIndex = 0,
                score = 0,
                selectedOptionId = null,
                isFinished = false
            )
        }
    }

    fun retryNewQuiz() {
        val state = _quizUiState.value

        _quizUiState.update {
            it.copy(
                currentIndex = 0,
                score = 0,
                selectedOptionId = null,
                isFinished = false,
                questions = emptyList()
            )
        }

        fetchQuestion(
            id = state.categoryId,
            difficulty = state.difficulty
        )
    }


    fun consumeFinishEvent() {
       _quizUiState.update {
            it.copy(isFinished = false)
        }
    }

}


object CategoryTextMapper {

    private val categoryMap = mapOf(
        9 to "General Knowledge",
        10 to "Entertainment: Books",
        11 to "Entertainment: Film",
        12 to "Entertainment: Music",
        13 to "Entertainment: Musicals & Theatres",
        14 to "Entertainment: Television",
        15 to "Entertainment: Video Games",
        16 to "Entertainment: Board Games",
        17 to "Science & Nature",
        18 to "Science: Computers",
        19 to "Science: Mathematics",
        20 to "Mythology",
        21 to "Sports",
        22 to "Geography",
        23 to "History",
        24 to "Politics",
        25 to "Art",
        26 to "Celebrities",
        27 to "Animals",
        28 to "Vehicles",
        29 to "Entertainment: Comics",
        30 to "Science: Gadgets",
        31 to "Entertainment: Japanese Anime & Manga",
        32 to "Entertainment: Cartoon & Animations"
    )

    fun getCategoryName(id: Int): String {
        return categoryMap[id] ?: "General Knowledge"
    }
}

