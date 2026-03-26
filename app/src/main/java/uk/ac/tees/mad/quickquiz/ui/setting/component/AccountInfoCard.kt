package uk.ac.tees.mad.quickquiz.ui.setting.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens

@Composable
fun AccountInfoCard() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(Dimens.CardPadding)
        ) {

            Row {
                Text(
                    text = "Current User",
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "user@example.com"
                )
            }

            Spacer(Modifier.height(Dimens.SpaceM))

            Row {
                Text(
                    text = "Last Score",
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "85%",
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(Modifier.height(Dimens.SpaceL))

            Text(
                text = "QUICKQUIZ V2.4.0",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
