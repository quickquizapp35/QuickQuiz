package uk.ac.tees.mad.quickquiz.utils


sealed class AuthError {
    object EmailAlreadyInUse : AuthError()
    object NetworkError : AuthError()
    object Unknown : AuthError()
}
