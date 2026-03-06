package uk.ac.tees.mad.quickquiz.utils



import com.google.firebase.auth.FirebaseAuthUserCollisionException
import java.io.IOException


fun mapFirebaseAuthException(exception: Exception): AuthError {
    return when (exception) {

        is FirebaseAuthUserCollisionException ->
            AuthError.EmailAlreadyInUse

        is IOException ->
            AuthError.NetworkError

        else ->
            AuthError.Unknown
    }
}
