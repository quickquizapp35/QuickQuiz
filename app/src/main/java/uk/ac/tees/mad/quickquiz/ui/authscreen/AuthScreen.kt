package uk.ac.tees.mad.quickquiz.ui.authscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.quickquiz.ui.authscreen.component.AuthForm
import uk.ac.tees.mad.quickquiz.ui.authscreen.component.AuthModeToggle
import uk.ac.tees.mad.quickquiz.ui.authscreen.component.AuthTopBar
import uk.ac.tees.mad.quickquiz.ui.authscreen.component.PrimaryActionButton
import uk.ac.tees.mad.quickquiz.ui.authscreen.component.WelcomeSection
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens
import uk.ac.tees.mad.quickquiz.utils.AuthMode
import uk.ac.tees.mad.quickquiz.utils.toMessage

@Composable
fun AuthScreen(
    authViewModel: AuthViewModel = viewModel(),
    onNavigateToHome: () -> Unit
){
    val uiState by authViewModel.authUiState.collectAsState()

    LaunchedEffect(uiState.isAuthenticated) {
        if(uiState.isAuthenticated){
            onNavigateToHome()
        }
    }

    AuthScreenContent(
        onSettingClick = {},
        uiState = uiState,
        onEmailChange = authViewModel::onEmailChange,
        onPasswordChange = authViewModel::onPasswordChange,
        onConfirmPasswordChange = authViewModel::onConfirmPasswordChange,
        onAuthModeChange = authViewModel::onAuthModeChange,
        onPrimaryActionClick = authViewModel::onPrimaryActionClick,
    )

}

@Composable
private fun AuthScreenContent(
    onSettingClick: () -> Unit,
    uiState: AuthUiState,
    onEmailChange:(String)-> Unit,
    onPasswordChange:(String)-> Unit,
    onConfirmPasswordChange:(String)-> Unit,
    onAuthModeChange:(AuthMode)-> Unit,
    onPrimaryActionClick:(AuthMode)-> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        AuthTopBar(
            onHelpClick = onSettingClick,
            modifier = Modifier
                .statusBarsPadding()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.ScreenPadding)
                .verticalScroll(rememberScrollState())
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(Dimens.SpaceXL))
            WelcomeSection()
            Spacer(Modifier.height(Dimens.SpaceXL))
            AuthModeToggle(
                selectedMode =  uiState.authMode,
                onModeSelected = onAuthModeChange
            )
            Spacer(
                modifier = Modifier
                    .height(Dimens.SpaceXL)
            )
            AuthForm(
                authMode = uiState.authMode,
                email = uiState.email,
                password = uiState.password,
                confirmPassword = uiState.confirmPassword,
                onEmailChange = onEmailChange,
                onPasswordChange = onPasswordChange,
                onConfirmPasswordChange = onConfirmPasswordChange,
            )
            Spacer(
                modifier = Modifier.height(Dimens.SpaceXL)
            )
            PrimaryActionButton(
                text =  uiState.authMode.actionText,
                enabled = uiState.canSubmit && !uiState.isLoading,
                loading = uiState.isLoading,
                onClick = { onPrimaryActionClick(uiState.authMode) },
            )
        }
        uiState.error?.let {error ->
            Spacer(Modifier.height(Dimens.SpaceL))
            Text(
                text = error.toMessage(),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AuthScreenPreview(){
    AuthScreen(onNavigateToHome = {})
}