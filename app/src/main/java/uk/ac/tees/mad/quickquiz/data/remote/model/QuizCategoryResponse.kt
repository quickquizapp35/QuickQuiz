package uk.ac.tees.mad.quickquiz.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class QuizCategoryResponse(
    @SerialName("trivia_categories")
    val triviaCategories: List<TriviaCategoryDto>
)
