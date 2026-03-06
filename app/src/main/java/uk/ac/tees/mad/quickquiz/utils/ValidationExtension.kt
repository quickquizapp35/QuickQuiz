package uk.ac.tees.mad.quickquiz.utils

import android.util.Patterns

fun String.isValidEmail(): Boolean {
    return isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
