package uk.ac.tees.mad.quickquiz.domain.repository

import uk.ac.tees.mad.quickquiz.domain.model.QuizCategory
import uk.ac.tees.mad.quickquiz.domain.model.QuizQuestion

interface QuizRepository{
    suspend fun getAllQuizCategory(): Result<List<QuizCategory>>
    suspend fun getQuizQuestions(category : Int , difficulty : String) : Result<List<QuizQuestion>>
}