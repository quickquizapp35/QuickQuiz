package uk.ac.tees.mad.quickquiz.utils

enum class QuizDifficulty(val apiPath : String) {
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard");
    fun displayName(): String = when (this) {
        EASY -> EASY.apiPath
        MEDIUM -> MEDIUM.apiPath
        HARD -> HARD.apiPath
    }
}