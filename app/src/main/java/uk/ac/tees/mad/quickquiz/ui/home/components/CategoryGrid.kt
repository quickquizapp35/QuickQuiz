package uk.ac.tees.mad.quickquiz.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens

@Composable
fun CategoryGrid(
    categories: List<UiCategory>,
    selectedCategory: UiCategory?,
    onCategoryClick: (UiCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxWidth(),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(Dimens.SpaceM),
        verticalArrangement = Arrangement.spacedBy(Dimens.SpaceM),
        userScrollEnabled = true
    ) {
        items(
            items = categories,
            key = { it.id }
        ) { category ->

            CategoryCard(
                category = category,
                selected = category.id == selectedCategory?.id,
                onClick = {
                    onCategoryClick(category)
                }
            )
        }
    }
}
