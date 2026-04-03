package uk.ac.tees.mad.quickquiz.ui.setting.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens


@Composable
fun SettingsTopBar(
    modifier: Modifier = Modifier,
    onNavBack: () -> Unit
) {
    Surface(
        modifier = modifier
            .height(Dimens.TopBarHeight),
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.ScreenPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = "back",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(Dimens.IconM).clickable{
                    onNavBack()
                }
            )
            Spacer(modifier.weight(1f))
            Text(
                text = "Settings",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier.weight(1f))
            Spacer(Modifier.width(40.dp))
        }
    }
}

