package uk.ac.tees.mad.quickquiz.ui.setting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.quickquiz.QuickQuizApp
import uk.ac.tees.mad.quickquiz.preference.AppPreference

class SettingViewModel (application: Application)
    : AndroidViewModel(application) {

        private val appPreference: AppPreference =
            (application as QuickQuizApp).appPreference
        private val firebaseAuth : FirebaseAuth =
            (application as QuickQuizApp).firebaseAuth

        private val firebaseRepository = (application as QuickQuizApp).firebaseRepository





    private val _settingUiState = MutableStateFlow(SettingUiState())
    val settingUiState = _settingUiState.asStateFlow()


    init{
       resolve()
    }

    private fun resolve(){
        viewModelScope.launch {
                 firebaseRepository
                .getLastScore()
                .onSuccess {  score->
                    _settingUiState.update {
                        it.copy(
                            lastScore = score,
                            isSoundEnabled = appPreference.isSoundEnabled,
                            isHapticEnabled = appPreference.isHapticEnabled,
                            user = firebaseAuth.currentUser?.email?:"abc@gmail.com",
                        )
                    }
                }
                .onFailure {  }
        }
        _settingUiState.update {
            it.copy(
                isSoundEnabled = appPreference.isSoundEnabled,
                isHapticEnabled = appPreference.isHapticEnabled,
                user = firebaseAuth.currentUser?.email?:"abc@gmail.com",
            )
        }
    }

    fun onHapticChange(isEnable : Boolean){
        appPreference.isHapticEnabled = isEnable
        _settingUiState.update {
            it.copy(isHapticEnabled = isEnable)
        }
    }

    fun onSoundChange(isEnable : Boolean){
        appPreference.isSoundEnabled = isEnable
        _settingUiState.update {
            it.copy(isSoundEnabled = isEnable)
        }
    }

    fun onLogoutClick(onSuccess:()-> Unit){
        viewModelScope.launch {
            firebaseAuth.signOut()
            delay(100)
            onSuccess()
        }
    }
}

