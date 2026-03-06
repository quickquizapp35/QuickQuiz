package uk.ac.tees.mad.quickquiz.ui.authscreen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens

@Composable
fun WelcomeSection(
    modifier : Modifier = Modifier
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WelcomeTitle()
        Spacer(Modifier.height(Dimens.SpaceS))
        WelcomeSubtitle()
    }
}

@Composable
private fun WelcomeTitle(){
    Text(
        text = "Welcome",
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
private fun WelcomeSubtitle(){
    Text(
        text = "Sign in to continue your learning journey with minimalist ease.",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
        textAlign = TextAlign.Center
    )
}