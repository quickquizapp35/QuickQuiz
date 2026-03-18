package uk.ac.tees.mad.quickquiz

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import uk.ac.tees.mad.quickquiz.data.remote.QuizApiService
import uk.ac.tees.mad.quickquiz.data.repository.AuthRepositoryImpl
import uk.ac.tees.mad.quickquiz.domain.repository.AuthRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import uk.ac.tees.mad.quickquiz.data.repository.QuizRepositoryImpl
import uk.ac.tees.mad.quickquiz.domain.repository.QuizRepository
import uk.ac.tees.mad.quickquiz.utils.Constants

class QuickQuizApp : Application() {

    val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    val firestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(
            firebaseAuth = firebaseAuth,
            firestore = firestore
        )
    }

    private val json by lazy {
        Json {
            ignoreUnknownKeys = true
        }
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()
    }

    val quizApiService: QuizApiService by lazy {
        retrofit.create(QuizApiService::class.java)
    }

    val quizRepository: QuizRepository by lazy {
        QuizRepositoryImpl(quizApiService)
    }
}

// this is an application class
// it is first called
//context will be injected from it