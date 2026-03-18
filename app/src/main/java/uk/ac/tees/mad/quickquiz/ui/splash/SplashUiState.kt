package uk.ac.tees.mad.quickquiz.ui.splash


sealed class  SplashUiState {
    object Loading : SplashUiState()
    object NavigateToAuth : SplashUiState()
    object NavigateToHome : SplashUiState()
}