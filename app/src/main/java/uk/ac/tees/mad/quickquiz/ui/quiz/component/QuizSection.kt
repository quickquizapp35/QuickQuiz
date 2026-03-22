package uk.ac.tees.mad.quickquiz.ui.quiz.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens

@Composable
fun QuestionSection(question: String) {
    Column {
        Text(
            text = question,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(Dimens.SpaceS))

        Text(
            text = "Choose the correct answer below",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
