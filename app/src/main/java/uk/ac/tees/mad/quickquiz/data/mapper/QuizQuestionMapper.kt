package uk.ac.tees.mad.quickquiz.data.mapper

import android.text.Html
import uk.ac.tees.mad.quickquiz.data.remote.model.QuizQuestionDto
import uk.ac.tees.mad.quickquiz.data.remote.model.QuizQuestionResponse
import uk.ac.tees.mad.quickquiz.domain.model.QuizQuestion


fun QuizQuestionResponse.toDomain(): List<QuizQuestion> {
    return results.map { it.toDomain() }
}



fun QuizQuestionDto.toDomain(): QuizQuestion {

    fun decode(text: String): String =
        Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY).toString()

//    val decodedCorrect = decode(correct_answer)
//    val decodedIncorrect = incorrect_answers.map { decode(it) }

    return QuizQuestion(
        type = type,
        difficulty = difficulty,
        category = category,
        question = question,
        correctAnswer = correctAnswer,
        incorrectAnswers = incorrectAnswers
    )
}