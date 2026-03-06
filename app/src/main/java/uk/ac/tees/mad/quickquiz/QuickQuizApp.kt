package uk.ac.tees.mad.quickquiz

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import uk.ac.tees.mad.quickquiz.data.repository.AuthRepositoryImpl
import uk.ac.tees.mad.quickquiz.domain.repository.AuthRepository

class QuickQuizApp : Application() {

       val firebaseAuth : FirebaseAuth by lazy {
           FirebaseAuth.getInstance()
       }
       val firestore : FirebaseFirestore by lazy {
          FirebaseFirestore.getInstance()
       }

       val authRepository : AuthRepository by lazy {
           AuthRepositoryImpl(
               firebaseAuth = firebaseAuth,
               firestore = firestore
           )
       }
    }

// this is an application class
// it is first called
//context will be injected from it