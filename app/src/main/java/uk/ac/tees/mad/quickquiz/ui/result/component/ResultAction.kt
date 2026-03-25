package uk.ac.tees.mad.quickquiz.ui.result.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.ac.tees.mad.quickquiz.ui.authscreen.component.PrimaryActionButton
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens

@Composable
fun ResultActions(
    onRetry: () -> Unit,
    onBackToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (modifier = modifier){
        PrimaryActionButton(
            text = "Retry Quiz",
            onClick = onRetry,
            enabled = true,
            loading = false,
            modifier = Modifier,
        )

        Spacer(Modifier.height(Dimens.SpaceM))

        OutlinedButton(
            onClick = onBackToHome,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Home")
        }
    }
}
