package uk.ac.tees.mad.quickquiz.ui.quiz.component.model

data class QuizOption(
    val id: String,
    val label: String,   // A, B, C, D
    val text: String,
    val isCorrect: Boolean
)