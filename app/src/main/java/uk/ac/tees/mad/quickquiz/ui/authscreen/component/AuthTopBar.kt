package uk.ac.tees.mad.quickquiz.ui.authscreen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import uk.ac.tees.mad.quickquiz.R
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens


@Composable
private fun AppName() {
    Text(
        text = "QuickQuiz",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground
    )
}


@Composable
private fun AppIcon() {
    Icon(
        painter = painterResource(
            R.drawable.flash_on
        ),
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary,
        modifier = Modifier.size(Dimens.IconM)
    )
}

@Composable
private fun AppBranding() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppIcon()
        Spacer(Modifier.width(Dimens.SpaceS))
        AppName()
    }
}

@Composable
private fun SettingAction(
    onClick: () -> Unit
) {
    Icon(
        imageVector = Icons.Default.Settings,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .size(Dimens.IconM)
            .clickable{
                onClick
            }
    )
}

@Composable
fun AuthTopBar(
    onHelpClick: () -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.ScreenPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AppBranding()
        SettingAction(onClick = onHelpClick)
    }
}




