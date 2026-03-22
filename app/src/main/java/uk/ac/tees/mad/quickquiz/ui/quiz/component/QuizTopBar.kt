package uk.ac.tees.mad.quickquiz.ui.quiz.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun QuizTopBar(
    onExit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onExit) {
            Icon(
                imageVector = Icons.Outlined.Close,
                contentDescription = null
            )
        }

        Spacer(Modifier.weight(1f))

        Text(
            text = "QuickQuiz",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.weight(1f))

       // TimerChip(time)
    }
}
