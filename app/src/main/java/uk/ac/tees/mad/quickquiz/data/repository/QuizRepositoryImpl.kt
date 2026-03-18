package uk.ac.tees.mad.quickquiz.data.repository

import uk.ac.tees.mad.quickquiz.data.mapper.toDomain
import uk.ac.tees.mad.quickquiz.data.remote.QuizApiService
import uk.ac.tees.mad.quickquiz.domain.model.QuizCategory
import uk.ac.tees.mad.quickquiz.domain.model.QuizQuestion
import uk.ac.tees.mad.quickquiz.domain.repository.QuizRepository

class QuizRepositoryImpl(private val triviaApiService: QuizApiService)
    : QuizRepository{

    private var cachedCategories: List<QuizCategory>? = null


    override suspend fun getAllQuizCategory(): Result<List<QuizCategory>> {
        cachedCategories?.let { it ->
            return Result.success(it)
        }

        return try {
            val response = triviaApiService
                .getQuestionCategories()
            val results = response.toDomain()
            cachedCategories = results
            Result.success(results)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getQuizQuestions(
        category: Int,
        difficulty: String
    ): Result<List<QuizQuestion>> {
        return try{
            val response = triviaApiService.getQuestions(
                category = category,
                difficulty = difficulty,
            )
            val results = response.toDomain()
            Result.success(results)
        }catch (e: Exception){
            Result.failure(e)
        }
    }

}