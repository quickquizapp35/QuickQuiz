package uk.ac.tees.mad.quickquiz.ui.setting

data class SettingUiState (
    val isSoundEnabled : Boolean = false,
    val isHapticEnabled : Boolean = false,
    val lastScore : Int  = 0,
    val user : String? = null
)