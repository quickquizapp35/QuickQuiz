package uk.ac.tees.mad.quickquiz.ui.authscreen.component

import androidx.compose.runtime.Composable
import uk.ac.tees.mad.quickquiz.utils.AuthMode

@Composable
fun AuthForm(
    authMode: AuthMode,
    email: String,
    password: String,
    confirmPassword: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit
) {
    when (authMode) {
        AuthMode.LOGIN -> LoginForm(
            email = email,
            password = password,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange
        )

        AuthMode.SIGN_UP -> SignUpForm(
            email = email,
            password = password,
            confirmPassword = confirmPassword,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onConfirmPasswordChange = onConfirmPasswordChange
        )
    }
}
