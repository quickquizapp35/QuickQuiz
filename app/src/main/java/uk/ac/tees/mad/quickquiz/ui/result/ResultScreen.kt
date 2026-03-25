package uk.ac.tees.mad.quickquiz.ui.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uk.ac.tees.mad.quickquiz.ui.result.component.PraiseChip
import uk.ac.tees.mad.quickquiz.ui.result.component.ResultActions
import uk.ac.tees.mad.quickquiz.ui.result.component.ResultHeader
import uk.ac.tees.mad.quickquiz.ui.result.component.ResultTopBar
import uk.ac.tees.mad.quickquiz.ui.result.component.ScoreRing
import uk.ac.tees.mad.quickquiz.ui.result.component.SummarySection
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens


@Composable
fun ResultScreen(
    uiState: ResultUiState,
    onRetry: () -> Unit,
    onBackToHome: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = Dimens.ScreenPadding)
    ) {

        ResultTopBar(
            onBack = onBack,
            modifier = Modifier.statusBarsPadding()
        )

        Spacer(Modifier.height(Dimens.SpaceXL))

        ResultHeader()

        Spacer(Modifier.height(Dimens.SpaceXL))

        ScoreRing(
            score = uiState.correctAnswers,
            total = uiState.totalQuestions,
            progress = uiState.scoreRatio
        )

        Spacer(Modifier.height(Dimens.SpaceL))

        PraiseChip(scoreRatio = uiState.scoreRatio)

        Spacer(Modifier.height(Dimens.SpaceXL))

        SummarySection(uiState)

        Spacer(Modifier.weight(1f))

        ResultActions(
            onRetry = onRetry,
            onBackToHome = onBackToHome,
            modifier = Modifier
        )

        Spacer(Modifier.height(Dimens.SpaceL))
    }
}


@Preview(showBackground = true)
@Composable
fun ResultScreenPreview(){
    ResultScreen(
        uiState = ResultUiState(
            categoryName = "Mathematics",
            totalQuestions = 10,
            correctAnswers = 7
        ),
        onRetry = {},
        onBackToHome = {},
        onBack = {}
    )
}