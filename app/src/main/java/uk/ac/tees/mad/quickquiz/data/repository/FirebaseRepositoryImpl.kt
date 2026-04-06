package uk.ac.tees.mad.quickquiz.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import uk.ac.tees.mad.quickquiz.domain.repository.FirebaseRepository

class FirebaseRepositoryImpl (private val firebaseAuth : FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore): FirebaseRepository {



    override suspend fun getLastScore(): Result<Int> {

        var score:Int = 0

        val user = firebaseAuth.currentUser
            ?: return Result.failure(Exception("User not logged in"))
        return try {
            val snapshot = firebaseFirestore
                .collection("users")
                .document(user.uid)
                .collection("results")
                .orderBy("createdAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .limit(1)
                .get()
                .await()

            val document = snapshot.documents.firstOrNull()

            if (document == null) {
                Result.success(null)
            } else {
                score = document.getLong("score")?.toInt() ?: 0
            }
                Result.success(score)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun saveScore(categoryId: Int,
                                   categoryName: String,
                                   difficulty: String,
                                   score: Int,
                                   totalQuestions: Int): Result<Unit> {

        val user = firebaseAuth.currentUser
            ?: return Result.failure(Exception("User not logged in"))

        return try {

            val percentage =
                if (totalQuestions == 0) 0f
                else score / totalQuestions.toFloat()

            val resultData = mapOf(
                "categoryId" to categoryId,
                "categoryName" to categoryName,
                "difficulty" to difficulty,
                "score" to score,
                "totalQuestions" to totalQuestions,
                "percentage" to percentage,
                "createdAt" to System.currentTimeMillis()
            )

            firebaseFirestore
                .collection("users")
                .document(user.uid)
                .collection("results")
                .add(resultData)
                .await()

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}