package uk.ac.tees.mad.quickquiz.utils

sealed class NetworkError(val message: String) {
    object Network : NetworkError("Please check your internet connection")
    object NoData : NetworkError("No data available")
    object Unknown : NetworkError("Unknown Error ")
}
