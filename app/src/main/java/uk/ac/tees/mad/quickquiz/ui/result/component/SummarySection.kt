package uk.ac.tees.mad.quickquiz.ui.result.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Science
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.ac.tees.mad.quickquiz.ui.quiz.QuizUiState
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens

@Composable
fun SummarySection(uiState: QuizUiState) {
    Column {
        Text(
            text = "Summary",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(Dimens.SpaceS))

        SummaryItem(
            icon = Icons.Outlined.Science,
            label = "Selected Subject",
            value = uiState.categoryName
        )

        SummaryItem(
            icon = Icons.Outlined.CheckCircle,
            label = "Correct Answers",
            value = uiState.score.toString(),
            valueColor = MaterialTheme.colorScheme.primary
        )

        SummaryItem(
            icon = Icons.Outlined.Cancel,
            label = "Incorrect Answers",
            value = uiState.incorrect.toString(),
            valueColor = MaterialTheme.colorScheme.error
        )
    }
}
