package uk.ac.tees.mad.quickquiz.ui.home.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens

@Composable
fun CategoryCard(
    category: UiCategory,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (selected)
            MaterialTheme.colorScheme.primaryContainer
        else
            MaterialTheme.colorScheme.surfaceVariant,
        label = "cardBackground"
    )

    val borderColor by animateColorAsState(
        targetValue = if (selected)
            MaterialTheme.colorScheme.primary
        else
            MaterialTheme.colorScheme.surfaceVariant,
        label = "cardBorder"
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.large,
        color = backgroundColor,
        border = BorderStroke(
            width = if (selected) Dimens.SpaceXS else Dimens.SpaceXS / 2,
            color = borderColor
        )
    ) {
        Column(
            modifier = Modifier.padding(Dimens.SpaceM),
            verticalArrangement = Arrangement.spacedBy(Dimens.SpaceXS)
        ) {

            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.size(Dimens.IconL + Dimens.SpaceS)
            ) {
                Icon(
                    imageVector = category.iconRes,
                    contentDescription = category.name,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(Dimens.SpaceS)
                        .size(Dimens.IconM)
                )
            }

            Spacer(modifier = Modifier.height(Dimens.SpaceS))

            Text(
                text = category.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = category.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun CategoryCardPreview() {
    CategoryCard(
        category = UiCategory(
            id = 1,
            name = "General Knowledge",
            description = "Questions about general knowledge",
            iconRes = Icons.Default.Favorite
        ),
        selected = true,
        onClick = {}
        )

}