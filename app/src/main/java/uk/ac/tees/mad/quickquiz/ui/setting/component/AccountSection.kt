package uk.ac.tees.mad.quickquiz.ui.setting.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens

@Composable
fun AccountSection() {
    Column {

        Text(
            text = "ACCOUNT",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(Dimens.SpaceM))

        Card {
            Column {

                AccountItem(
                    icon = Icons.Outlined.Person,
                    title = "Edit Profile"
                )

                Divider()

                AccountItem(
                    icon = Icons.Outlined.Security,
                    title = "Privacy & Security"
                )
            }
        }
    }
}
