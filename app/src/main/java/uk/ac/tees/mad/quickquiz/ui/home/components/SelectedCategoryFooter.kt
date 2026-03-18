package uk.ac.tees.mad.quickquiz.ui.home.components



import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.quickquiz.ui.authscreen.component.PrimaryActionButton
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens
import uk.ac.tees.mad.quickquiz.utils.QuizDifficulty

@Composable
fun SelectedCategoryFooter(
    selected : QuizDifficulty,
    onDifficultySelect: (QuizDifficulty) -> Unit,
    category: UiCategory?,
    onStartClick: (UiCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    if (category == null) return

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = Dimens.ScreenPadding,
                    vertical = Dimens.SpaceM
                ),
            verticalArrangement = Arrangement.spacedBy(Dimens.SpaceM)
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(Dimens.SpaceXS)
            ) {
                Text(
                    text = "Selected Category",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )

                Text(
                    text = category.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            DifficultySelector(
                selected = selected,
                onSelect = onDifficultySelect
            )

            PrimaryActionButton(
                modifier = Modifier
                    .navigationBarsPadding(),
                text = "Start Quiz",
                enabled = true,
                loading = false,
                onClick = { onStartClick(category) }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SelectedUiCategoryPreview(){

    SelectedCategoryFooter(
        category = UiCategory(
            id = 1,
            name = "name",
            description = "alpha beta gama",
            iconRes = Icons.Default.Favorite
        ),
        onStartClick = {},
        modifier = Modifier,
        selected = QuizDifficulty.EASY,
        onDifficultySelect = {}
    )
}