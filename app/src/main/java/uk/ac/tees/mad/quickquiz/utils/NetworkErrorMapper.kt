package uk.ac.tees.mad.quickquiz.utils

fun Throwable.NetworkError(): NetworkError {
    return when (this) {
        is java.io.IOException -> NetworkError.Network
        else -> NetworkError.Unknown
    }
}
