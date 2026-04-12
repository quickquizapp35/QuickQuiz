package uk.ac.tees.mad.quickquiz.data.mapper

import android.os.Build
import android.text.Html
import uk.ac.tees.mad.quickquiz.data.remote.model.QuizQuestionDto
import uk.ac.tees.mad.quickquiz.data.remote.model.QuizQuestionResponse
import uk.ac.tees.mad.quickquiz.domain.model.QuizQuestion
fun QuizQuestionResponse.toDomain(): List<QuizQuestion> {
    return results.map { it.toDomain() }
}

fun QuizQuestionDto.toDomain(): QuizQuestion {

    fun decode(text: String): String {
        return Html.fromHtml(text).toString()

    }

    return QuizQuestion(
        type = type,
        difficulty = difficulty,
        category = decode(category),
        question = decode(question),
        correctAnswer = decode(correctAnswer),
        incorrectAnswers = incorrectAnswers.map { decode(it) }
    )
}