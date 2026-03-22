package uk.ac.tees.mad.quickquiz.ui.quiz.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import uk.ac.tees.mad.quickquiz.ui.quiz.component.model.QuizOption
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens

@Composable
fun OptionsSection(
    options: List<QuizOption>,
    selectedOptionId: String?,
    onOptionSelect: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement
        .spacedBy(Dimens.SpaceM)) {
        options.forEach { option ->
            OptionCard(
                option = option,
                selected = option.id == selectedOptionId,
                onClick = { onOptionSelect(option.id) }
            )
        }
    }
}
