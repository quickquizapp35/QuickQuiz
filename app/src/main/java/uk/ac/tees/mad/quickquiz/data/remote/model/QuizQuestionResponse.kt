package uk.ac.tees.mad.quickquiz.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuizQuestionResponse(
    @SerialName("response_code")
    val responseCode: Int,
    @SerialName("results")
    val results: List<QuizQuestionDto>
)