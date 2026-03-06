package uk.ac.tees.mad.quickquiz.ui.authscreen

import uk.ac.tees.mad.quickquiz.utils.AuthError
import uk.ac.tees.mad.quickquiz.utils.AuthMode
import uk.ac.tees.mad.quickquiz.utils.isValidEmail

data class AuthUiState (
    val email :String = "",
    val password : String = "",
    val confirmPassword : String = "",
    val isLoading : Boolean = false,
    val error : AuthError ? = null,
    val authMode : AuthMode = AuthMode.LOGIN,
    val isAuthenticated : Boolean = false
){
    val canSubmit: Boolean
        get() = when (authMode) {
            AuthMode.LOGIN ->
                email.isValidEmail() && password.length >= 8

            AuthMode.SIGN_UP ->
                email.isValidEmail() &&
                        password.length >= 8 &&
                        password == confirmPassword
        }

}

