package uk.ac.tees.mad.quickquiz.utils


enum class AuthMode {
    LOGIN,
    SIGN_UP;

    val actionText: String
        get() = when (this) {
            LOGIN -> "login"
            SIGN_UP -> "signup"
        }
}
