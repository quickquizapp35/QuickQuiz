package uk.ac.tees.mad.quickquiz.ui.quiz.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.ac.tees.mad.quickquiz.ui.authscreen.component.PrimaryActionButton
import uk.ac.tees.mad.quickquiz.ui.quiz.component.model.QuizOption
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens

@Composable
fun BottomActionSection(
    selectedOption: QuizOption?,
    canConfirm: Boolean,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = Dimens.ScreenPadding)
    ) {

        AnimatedVisibility(visible = selectedOption != null) {
            SelectionPreview(selectedOption = selectedOption)
        }

        Spacer(modifier = Modifier.height(Dimens.SpaceM))

        PrimaryActionButton(
            text = "Confirm Answer",
            enabled = canConfirm,
            onClick = onConfirm,
            loading = false
        )

        Spacer(Modifier.height(Dimens.SpaceL))
    }
}
