package uk.ac.tees.mad.quickquiz.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import uk.ac.tees.mad.quickquiz.data.remote.model.QuizCategoryResponse
import uk.ac.tees.mad.quickquiz.data.remote.model.QuizQuestionResponse

import uk.ac.tees.mad.quickquiz.utils.Constants

interface QuizApiService {
    @GET("api.php")
    suspend fun getQuestions(
        @Query("amount") amount: Int = Constants.AMOUNT,
        @Query("category") category: Int,
        @Query("difficulty") difficulty: String,
        @Query("type") type: String = Constants.TYPE
    ) : QuizQuestionResponse

    @GET("api_category.php")
    suspend fun getQuestionCategories() : QuizCategoryResponse

}