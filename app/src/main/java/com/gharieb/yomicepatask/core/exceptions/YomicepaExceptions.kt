package com.gharieb.yomicepatask.core.exceptions

sealed class YomicepaExceptions(message: String) : Exception(message) {

    // network
    sealed class Network(message: String): YomicepaExceptions(message){
        object SocketTimeoutException: Network(message = "Network request timed out, please check your connection and try again")
        object UnknownHostException: Network(message = "Unable to contact server, please check your connection and try again")
        object ConnectException: Network(message = "Connection to remote host could not be established, please check your connection and try again")
        object SSLHandshakeException: Network(message = "An error occurred, please try again")
    }

    // http
    sealed class HTTP(message: String): YomicepaExceptions(message){
        data class Unauthorized(private val errorCode: Int): HTTP(message = "Error code: $errorCode, Failed: Unauthorized request")
        data class InternalServerError(private val errorCode: Int): HTTP(message = "Error code: $errorCode, There's a server side error")
        data class AccessDenied(private val errorCode: Int): HTTP(message = "Error code: $errorCode, Access denied, make sure you have permission to access the requested data")
        data class NotFound(private val errorCode: Int): HTTP(message = "Error code: $errorCode, Can not find requested data")
        data class UnknownHttpError(private val errorCode: Int): HTTP(message = "Error code: $errorCode, Unknown HTTP error")
    }

    // data
    data class IOException(private val errorMessage: String = "Input/output operation failed") : YomicepaExceptions(errorMessage)
    data class CustomException(private val errorMessage: String = "Error Occurred") : YomicepaExceptions(errorMessage)

    // unknown
    object UnknownException: YomicepaExceptions(message = "Unknown exception")

}