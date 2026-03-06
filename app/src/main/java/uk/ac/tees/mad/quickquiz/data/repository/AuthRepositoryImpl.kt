package uk.ac.tees.mad.quickquiz.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import uk.ac.tees.mad.quickquiz.domain.repository.AuthRepository

const val USERS_COLLECTION = "users"



class AuthRepositoryImpl(private val firebaseAuth: FirebaseAuth,
                       private val firestore : FirebaseFirestore)
    : AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Result<Unit> {
        return try {
            firebaseAuth
                .signInWithEmailAndPassword(email, password)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signup(email: String, password: String) :Result<Unit> {
        return try {
            val authResult = firebaseAuth
                .createUserWithEmailAndPassword(email , password)
                .await()
            val user = authResult.user?: throw Exception("user creation failed")

            val userData = mapOf(
                "uid" to user.uid,
                "email" to user.email,
                "createdAt" to System.currentTimeMillis()
            )

            try {
                firestore
                    .collection(USERS_COLLECTION)
                    .document(user.uid)
                    .set(userData)
                    .await()
            } catch (e: Exception) {
                user.delete().await()
                throw e
            }

            Result.success(Unit)
        }catch(e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun logout() {
            firebaseAuth.signOut()
    }

}