package uk.ac.tees.mad.quickquiz.ui.result


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uk.ac.tees.mad.quickquiz.ui.quiz.QuizViewModel
import uk.ac.tees.mad.quickquiz.ui.result.component.PraiseChip
import uk.ac.tees.mad.quickquiz.ui.result.component.ResultActions
import uk.ac.tees.mad.quickquiz.ui.result.component.ResultHeader
import uk.ac.tees.mad.quickquiz.ui.result.component.ResultTopBar
import uk.ac.tees.mad.quickquiz.ui.result.component.ScoreRing
import uk.ac.tees.mad.quickquiz.ui.result.component.SummarySection
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens


@Composable
fun ResultScreen(
    viewModel: QuizViewModel,
    onRetrySame: () -> Unit,
    onRetryNew: () -> Unit,
    onBackToHome: () -> Unit,
    onNavBack:()-> Unit
) {
    val uiState by viewModel.quizUiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ){

        Column(
            modifier = Modifier
                .fillMaxSize()
        ){
            ResultTopBar(
                onBack = onNavBack,
                modifier = Modifier.statusBarsPadding()
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = Dimens.ScreenPadding)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.Start
            ) {

                Spacer(Modifier.height(Dimens.SpaceS))

                ResultHeader()

                Spacer(Modifier.height(Dimens.SpaceS))

                ScoreRing(
                    score = uiState.score,
                    total = uiState.totaQuestion,
                    progress = uiState.percentageCorrect,
                )

                Spacer(Modifier.height(Dimens.SpaceS))

                PraiseChip(scoreRatio = uiState.progress)

                Spacer(Modifier.height(Dimens.SpaceS))

                SummarySection(uiState = uiState)

            }
            ResultActions(
                modifier = Modifier
                    .padding(horizontal = Dimens.ScreenPadding)
                    .navigationBarsPadding(),
                onRetryNew = {
                    viewModel
                        .saveQuizAnswer()
                    onRetryNew()
                },
                onRetrySame = onRetrySame,
                onBackToHome = {
                    viewModel
                        .saveQuizAnswer()
                    onBackToHome()
                }
            )
        }
    }
}
