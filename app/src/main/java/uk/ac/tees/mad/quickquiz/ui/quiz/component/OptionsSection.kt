package uk.ac.tees.mad.quickquiz.ui.quiz.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.ac.tees.mad.quickquiz.ui.quiz.component.model.QuizOption
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens

@Composable
fun OptionsSection(
    options: List<QuizOption>,
    selectedOptionId: String?,
    onOptionSelect: (String) -> Unit,
    showResult: Boolean,
    isHapticEnabled: Boolean
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement
        .spacedBy(Dimens.SpaceM)) {
        options.forEach { option ->
            OptionCard(
                option = option,
                showResult = showResult,
                selected = option.id == selectedOptionId,
                onClick = { onOptionSelect(option.id) },
                isHapticEnabled = isHapticEnabled
            )
        }
    }
}
