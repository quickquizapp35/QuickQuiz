package uk.ac.tees.mad.quickquiz.domain.model

data class QuizQuestion (
    val type : String,
    val difficulty : String,
    val category : String,
    val question : String,
    val correctAnswer : String,
    val incorrectAnswers : List<String>
)
