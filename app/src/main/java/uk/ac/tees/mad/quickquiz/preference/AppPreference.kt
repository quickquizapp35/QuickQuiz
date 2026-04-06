package uk.ac.tees.mad.quickquiz.preference

import android.content.Context
import android.content.SharedPreferences

class AppPreference(context : Context){

    private val prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences(
            "quick_quiz_prefs",
            Context.MODE_PRIVATE
        )

    companion object {
        private const val KEY_SOUND_ENABLED = "key_sound_enabled"
        private const val KEY_VIBRATION_ENABLED = "key_vibration_enabled"
    }


    var isSoundEnabled: Boolean
        get() = prefs.getBoolean(KEY_SOUND_ENABLED, true)
        set(value) = prefs.edit().putBoolean(KEY_SOUND_ENABLED, value).apply()

    var isHapticEnabled: Boolean
        get() = prefs.getBoolean(KEY_VIBRATION_ENABLED, true)
        set(value) = prefs.edit().putBoolean(KEY_VIBRATION_ENABLED, value).apply()

}