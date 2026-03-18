package uk.ac.tees.mad.quickquiz.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens
import uk.ac.tees.mad.quickquiz.utils.QuizDifficulty

@Composable
private fun DifficultyItem(
    difficulty: QuizDifficulty,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    val textColor = if (selected) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    Surface(
        modifier = modifier
            .height(Dimens.ButtonHeight),
        shape = RoundedCornerShape(50),
        color = backgroundColor,
        tonalElevation = if (selected) 2.dp else 0.dp,
        onClick = onClick
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = difficulty.displayName(),
                style = MaterialTheme.typography.labelLarge,
                color = textColor
            )
        }
    }
}


@Composable
fun DifficultySelector(
    selected: QuizDifficulty,
    onSelect: (QuizDifficulty) -> Unit,
    modifier: Modifier = Modifier
) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.ScreenPadding),
            horizontalArrangement = Arrangement.spacedBy(Dimens.SpaceS)
        ) {
            QuizDifficulty.entries.forEach { difficulty ->
                DifficultyItem(
                    difficulty = difficulty,
                    selected = difficulty == selected,
                    onClick = { onSelect(difficulty) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
