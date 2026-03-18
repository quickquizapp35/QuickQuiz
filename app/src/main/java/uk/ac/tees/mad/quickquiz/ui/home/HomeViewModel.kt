package uk.ac.tees.mad.quickquiz.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.quickquiz.QuickQuizApp
import uk.ac.tees.mad.quickquiz.domain.repository.QuizRepository
import uk.ac.tees.mad.quickquiz.ui.home.components.CategoryUiMapper
import uk.ac.tees.mad.quickquiz.ui.home.components.UiCategory
import uk.ac.tees.mad.quickquiz.utils.NetworkError
import uk.ac.tees.mad.quickquiz.utils.QuizDifficulty

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val quizRepository : QuizRepository =
        (application as QuickQuizApp).quizRepository


    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    init {
        loadCategories()
    }

    fun loadCategories(){
        viewModelScope.launch {
            _homeUiState.update {
                it.copy(isLoading = true)
            }

            val result = quizRepository
                .getAllQuizCategory()
            result
                .onSuccess {domainCategories->

                    val uiCategories = domainCategories.map{
                        CategoryUiMapper.map(it)
                    }
                    _homeUiState.update {
                        it.copy(
                            isLoading = false,
                            error= null,
                            categories = uiCategories
                        )
                    }
            }.onFailure {failure->
                _homeUiState.update {
                    it.copy(
                        isLoading = false,
                        error = failure.NetworkError().message
                    )
                }
            }
        }
    }

    fun onCategorySelect(category: UiCategory){
        _homeUiState.update {
            it.copy(
                selectedCategory = category
            )
        }
    }

    fun onDifficultySelect(selected : QuizDifficulty){
        _homeUiState.update {
            it.copy(
                selectedDifficulty = selected
            )
        }
    }
}