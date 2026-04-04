package uk.ac.tees.mad.quickquiz.ui.result.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.quickquiz.ui.authscreen.component.PrimaryActionButton
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens

@Composable
fun ResultActions(
    modifier: Modifier = Modifier,
    onRetryNew: () -> Unit,
    onRetrySame: () -> Unit,
    onBackToHome: () -> Unit,

) {
    Column (modifier = modifier){

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Row(modifier = Modifier.weight(1f)){
                PrimaryActionButton(
                    text = "Retry Quiz",
                    onClick = onRetrySame,
                    enabled = true,
                    loading = false,
                    modifier = Modifier,
                )
            }
            Spacer(Modifier.width(2.dp))
            Row(modifier = Modifier.weight(1f)){
                PrimaryActionButton(
                    text = "New Quiz",
                    onClick = onRetryNew,
                    enabled = true,
                    loading = false,
                    modifier = Modifier,
                )
            }
        }
        Spacer(Modifier.height(Dimens.SpaceM))

        OutlinedButton(
            onClick = onBackToHome,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Home")
        }
    }
}
