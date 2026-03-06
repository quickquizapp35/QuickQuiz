package uk.ac.tees.mad.quickquiz.ui.authscreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens
import uk.ac.tees.mad.quickquiz.utils.AuthMode

@Composable
fun AuthModeToggle(
    selectedMode: AuthMode,
    onModeSelected: (AuthMode) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.large
            )
            .padding(Dimens.SpaceXS)
    ) {
        AuthModeItem(
            text = "Login",
            selected = selectedMode == AuthMode.LOGIN,
            onClick = { onModeSelected(AuthMode.LOGIN) },
            modifier = Modifier.weight(1f)
        )

        AuthModeItem(
            text = "Sign Up",
            selected = selectedMode == AuthMode.SIGN_UP,
            onClick = { onModeSelected(AuthMode.SIGN_UP) },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun AuthModeItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor =
        if (selected) MaterialTheme.colorScheme.surface
        else Color.Transparent

    val textColor =
        if (selected) MaterialTheme.colorScheme.onSurface
        else MaterialTheme.colorScheme.onSurfaceVariant

    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(vertical = Dimens.SpaceS),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = textColor
        )
    }
}

