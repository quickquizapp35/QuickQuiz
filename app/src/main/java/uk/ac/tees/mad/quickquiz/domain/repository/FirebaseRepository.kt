package uk.ac.tees.mad.quickquiz.domain.repository

interface FirebaseRepository{

    suspend fun  getLastScore() : Result<Int>

    suspend fun saveScore(
        categoryId: Int,
        categoryName: String,
        difficulty: String,
        score: Int,
        totalQuestions: Int
    ) :Result<Unit>

}