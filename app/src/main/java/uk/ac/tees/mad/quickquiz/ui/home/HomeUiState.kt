package uk.ac.tees.mad.quickquiz.ui.home

import uk.ac.tees.mad.quickquiz.ui.home.components.UiCategory
import uk.ac.tees.mad.quickquiz.utils.QuizDifficulty

data class HomeUiState(
    val isLoading : Boolean = false,
    val error : String? = null,
    val categories: List<UiCategory> = emptyList(),
    val selectedCategory: UiCategory? = null,
    val selectedDifficulty: QuizDifficulty = QuizDifficulty.MEDIUM
)
