package uk.ac.tees.mad.quickquiz.ui.authscreen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens

@Composable
fun SignUpForm(
    email: String,
    password: String,
    confirmPassword: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit
) {
    Column {
        EmailField(
            value = email,
            onValueChange = onEmailChange
        )

        Spacer(Modifier.height(Dimens.SpaceM))

        PasswordField(
            value = password,
            onValueChange = onPasswordChange,
            imeAction = ImeAction.Next
        )

        Spacer(Modifier.height(Dimens.SpaceM))

        ConfirmPasswordField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange
        )
    }
}
