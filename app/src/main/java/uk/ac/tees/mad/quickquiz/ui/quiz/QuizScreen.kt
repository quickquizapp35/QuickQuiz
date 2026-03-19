package uk.ac.tees.mad.quickquiz.ui.quiz

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun QuizScreen(
    id: Int,
    difficulty: String,
    viewModel: QuizViewModel = viewModel()
) {

    val uiState by viewModel.quizUiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.fetchQuestion(
            id = id,
            difficulty = difficulty
        )
    }



    LazyColumn {
        items(uiState.questionList){item->
            Text(
                text = item.question
            )
        }

    }


}