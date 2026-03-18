package uk.ac.tees.mad.quickquiz.ui.authscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.quickquiz.QuickQuizApp
import uk.ac.tees.mad.quickquiz.domain.repository.AuthRepository
import uk.ac.tees.mad.quickquiz.utils.AuthMode
import uk.ac.tees.mad.quickquiz.utils.mapFirebaseAuthException


class AuthViewModel(application : Application )
    : AndroidViewModel(application) {
    private val authRepository: AuthRepository =
        (application as QuickQuizApp).authRepository
    private val _authUiState = MutableStateFlow(AuthUiState())
    val authUiState = _authUiState.asStateFlow()


    fun onAuthModeChange(authMode : AuthMode){
        _authUiState.update {
            it.copy(
                authMode = authMode,
                email = "",
                password = "",
                confirmPassword = ""
            )
        }
    }

    fun onEmailChange(email: String){
        _authUiState.update {
            it.copy(
                email = email
            )
        }
    }

    fun onPasswordChange(password: String){
        _authUiState.update {
            it.copy(
                password = password
            )
        }
    }

    fun onConfirmPasswordChange(confirmPassword: String){
        _authUiState.update {
            it.copy(
                confirmPassword = confirmPassword
            )
        }
    }

    fun onPrimaryActionClick(authMode: AuthMode) {

        viewModelScope.launch {
            _authUiState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }
            val result = when (authMode) {
                AuthMode.LOGIN -> {
                    authRepository.login(
                        email = _authUiState.value.email,
                        password = _authUiState.value.password
                    )
                }
                AuthMode.SIGN_UP -> {
                    authRepository.signup(
                        email = _authUiState.value.email,
                        password = _authUiState.value.password
                    )
                }
            }
            result.fold(
                onSuccess = {
                    _authUiState.update {
                        it.copy(
                            isLoading = false,
                            isAuthenticated = true,
                            error = null
                        )
                    }
                },
                onFailure = { exception ->
                    _authUiState.update {
                     it.copy(
                         isLoading = false,
                         error = mapFirebaseAuthException(Exception(exception))
                       )
                    }
                }
            )
        }
    }
}