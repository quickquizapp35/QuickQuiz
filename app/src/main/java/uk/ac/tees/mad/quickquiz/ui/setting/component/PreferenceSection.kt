package uk.ac.tees.mad.quickquiz.ui.setting.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Vibration
import androidx.compose.material.icons.outlined.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens

@Composable
fun PreferenceSection(isHapticEnabled: Boolean = true,
                      isSoundEnabled: Boolean = true,
                      onHapticEnabledChange: (Boolean) -> Unit,
                      onSoundEnabledChange: (Boolean) -> Unit ) {
    Column {
        Spacer(Modifier.height(Dimens.SpaceM))

        Card {
            Column {

                PreferenceToggleItem(
                    icon = Icons.Outlined.VolumeUp,
                    title = "Sound Effects",
                    checked = isSoundEnabled,
                    onCheckedChange = onSoundEnabledChange
                )

                Divider()

                PreferenceToggleItem(
                    icon = Icons.Outlined.Vibration,
                    title = "Haptic Feedback",
                    checked = isHapticEnabled,
                    onCheckedChange = onHapticEnabledChange
                )
            }
        }
    }
}
