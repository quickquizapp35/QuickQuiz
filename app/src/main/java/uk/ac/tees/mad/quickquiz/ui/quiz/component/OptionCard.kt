package uk.ac.tees.mad.quickquiz.ui.quiz.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.quickquiz.ui.quiz.component.model.QuizOption
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens

@Composable
fun OptionCard(
    option: QuizOption,
    selected: Boolean,
    showResult: Boolean,
    onClick: () -> Unit,
    isHapticEnabled: Boolean
) {

    val haptic = LocalHapticFeedback.current

    val containerColor = when {
        showResult && option.isCorrect ->
            MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)

        showResult && selected ->
            MaterialTheme.colorScheme.error.copy(alpha = 0.15f)

        else ->
            MaterialTheme.colorScheme.surface
    }

    val borderColor = when {
        showResult && option.isCorrect ->
            MaterialTheme.colorScheme.primary

        showResult && selected ->
            MaterialTheme.colorScheme.error

        selected ->
            MaterialTheme.colorScheme.primary

        else ->
            MaterialTheme.colorScheme.outline
    }

    Card(
        onClick = {
            if (!showResult) {
                onClick()
                if (isHapticEnabled) {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                }
            }
        },
        colors = CardDefaults.cardColors(containerColor = containerColor),
        border = BorderStroke(1.dp, borderColor)
    ) {
        Row(
            modifier = Modifier.padding(Dimens.CardPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OptionBadge(option.label, selected)
            Spacer(Modifier.width(Dimens.SpaceM))
            Text(option.text, Modifier.weight(1f))
            RadioButton(selected = selected, onClick = null)
        }
    }
}

