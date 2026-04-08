package uk.ac.tees.mad.quickquiz.ui.quiz

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uk.ac.tees.mad.quickquiz.R
import uk.ac.tees.mad.quickquiz.ui.quiz.component.BottomActionSection
import uk.ac.tees.mad.quickquiz.ui.quiz.component.OptionsSection
import uk.ac.tees.mad.quickquiz.ui.quiz.component.ProgressSection
import uk.ac.tees.mad.quickquiz.ui.quiz.component.QuestionSection
import uk.ac.tees.mad.quickquiz.ui.quiz.component.QuizTopBar
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens

@Composable
fun QuizScreen(
    id: Int,
    difficulty: String,
    onNavigateToResult:()-> Unit,
    onNavigateToHome:()-> Unit,
    viewModel: QuizViewModel,
) {

    val uiState by viewModel.quizUiState.collectAsStateWithLifecycle()


    val context = LocalContext.current

    if(uiState.isSoundEnabled){
        LaunchedEffect(Unit) {
            viewModel.events.collect { event ->
                when (event) {
                    QuizUiEvent.PlayCorrectSound -> {
                        playSound(context, R.raw.success)
                    }
                    QuizUiEvent.PlayWrongSound -> {
                        playSound(context, R.raw.failure)
                    }
                }
            }
        }
    }
    LaunchedEffect(id , difficulty) {
        if (uiState.questions.isEmpty()) {
            viewModel.fetchQuestion(id, difficulty)
        }
    }


    LaunchedEffect(uiState.isFinished) {
        if (uiState.isFinished) {
            onNavigateToResult()
            viewModel.consumeFinishEvent()
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Column(modifier = Modifier.fillMaxSize()){

            QuizTopBar(
                onExit = onNavigateToHome,
                modifier = Modifier
                    .statusBarsPadding()
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = Dimens.ScreenPadding),
                horizontalAlignment = Alignment.Start
            ) {

                if(uiState.isLoading){
                    Box(modifier = Modifier
                        .fillMaxSize(),
                        contentAlignment = Alignment.Center){
                        CircularProgressIndicator()
                    }
                }

                Spacer(Modifier.height(Dimens.SpaceM))

                ProgressSection(
                    current = uiState.currentIndex + 1,
                    total = uiState.totaQuestion,
                    progress = uiState.progress
                )

                Spacer(Modifier.height(Dimens.SpaceM))

                QuestionSection(
                    question = uiState.currentQuestion?.question ?: ""
                )

                Spacer(Modifier.height(Dimens.SpaceL))

                Box {
                    OptionsSection(
                        options = uiState.currentQuestion?.options ?: emptyList(),
                        selectedOptionId = uiState.selectedOptionId,
                        showResult = uiState.showAnswerResult,
                        onOptionSelect = viewModel::onOptionSelected,
                        isHapticEnabled = uiState.isHapticEnabled
                    )
                }
                Spacer(
                    modifier = Modifier.height(Dimens.BottomBarHeight)
                )
            }
        }
        BottomActionSection(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding(),
            selectedOption = uiState.currentQuestion
                ?.options
                ?.find { it.id == uiState.selectedOptionId },
            canConfirm = uiState.canConfirm,
            onConfirm = viewModel::onConfirmAnswer
        )
    }
}

fun playSound(context: Context, resId: Int) {
    val mediaPlayer = MediaPlayer.create(context, resId)
    mediaPlayer.setOnCompletionListener {
        it.release()
    }
    mediaPlayer.start()
}

