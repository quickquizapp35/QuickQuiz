package uk.ac.tees.mad.quickquiz.utils

fun AuthError.toMessage(): String = when (this) {
    AuthError.EmailAlreadyInUse ->
        "This email is already registered. Try logging in."

    AuthError.NetworkError ->
        "Network error. Please check your internet connection."

    AuthError.Unknown ->
        "Something went wrong. Please try again."
}
