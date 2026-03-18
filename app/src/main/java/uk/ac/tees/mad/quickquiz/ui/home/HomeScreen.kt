package uk.ac.tees.mad.quickquiz.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.quickquiz.ui.home.components.CategoryGrid
import uk.ac.tees.mad.quickquiz.ui.home.components.ErrorSection
import uk.ac.tees.mad.quickquiz.ui.home.components.HomeHeader
import uk.ac.tees.mad.quickquiz.ui.home.components.HomeTopBar
import uk.ac.tees.mad.quickquiz.ui.home.components.SelectedCategoryFooter
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    onSettingClick:()-> Unit
){
    val uiState by homeViewModel.homeUiState.collectAsStateWithLifecycle()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {


            HomeTopBar(
               onSettingClick = onSettingClick,
                modifier = Modifier.statusBarsPadding()
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = Dimens.ScreenPadding),
                horizontalAlignment = Alignment.Start
            ) {

                Spacer(modifier = Modifier.height(Dimens.SpaceS))

                HomeHeader()

                Spacer(modifier = Modifier.height(Dimens.SpaceM))

                when {
                    uiState.isLoading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = Dimens.SpaceXL),
                            contentAlignment = Alignment.Center
                        ) {
                           CircularProgressIndicator()
                        }
                    }

                    uiState.error != null -> {
                        ErrorSection(
                            message = uiState.error?:"No error",
                            onRetry = { homeViewModel.loadCategories() }
                        )
                    }

                    else -> {
                        CategoryGrid(
                            categories = uiState.categories,
                            selectedCategory = uiState.selectedCategory,
                            onCategoryClick = { category ->
                                homeViewModel.onCategorySelect(category)
                            },
                           modifier = if(uiState.selectedCategory == null) Modifier.navigationBarsPadding() else Modifier
                        )
                    }
                }
              //  Spacer(modifier = Modifier.height(Dimens.SpaceXL))
            }

            SelectedCategoryFooter(
                category = uiState.selectedCategory,
                onStartClick = { selected ->
                    //navigate to quiz screen with id of selected category
                },
                selected = uiState.selectedDifficulty,
                onDifficultySelect = {
                    homeViewModel.onDifficultySelect(it)
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview(){
    HomeScreen(onSettingClick = {})
}