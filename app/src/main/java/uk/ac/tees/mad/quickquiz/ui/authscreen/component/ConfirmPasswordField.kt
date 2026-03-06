package uk.ac.tees.mad.quickquiz.ui.authscreen.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import uk.ac.tees.mad.quickquiz.R
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens

@Composable
fun ConfirmPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Done
) {
    var visible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.InputHeight),
        label = { Text("Confirm Password") },
        singleLine = true,
        shape = MaterialTheme.shapes.medium,
        visualTransformation = if (visible)
            VisualTransformation.None
        else
            PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { visible = !visible }) {
                Icon(
                    painter = if (visible)
                        painterResource(R.drawable.outline_visibility_24)
                    else
                        painterResource(R.drawable.outline_visibility_off_24),
                    contentDescription = null,
                )
            }
        }
    )
}
