package com.gharieb.yomicepatask.core.exceptions

import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

object ExceptionMapper {
    fun mapExceptionToSolutionXException(exception: Exception): YomicepaExceptions {
        return when (exception) {
            is UnknownHostException -> YomicepaExceptions.Network.UnknownHostException
            is SocketTimeoutException -> YomicepaExceptions.Network.SocketTimeoutException
            is ConnectException -> YomicepaExceptions.Network.ConnectException
            is SSLHandshakeException -> YomicepaExceptions.Network.SSLHandshakeException
            is HttpException -> {
                if (exception.code() == 401) {
                    YomicepaExceptions.HTTP.Unauthorized(exception.code())
                } else if (exception.code() == 403) {
                    YomicepaExceptions.HTTP.AccessDenied(exception.code())
                } else if (exception.code() == 404) {
                    YomicepaExceptions.HTTP.NotFound(exception.code())
                } else if (exception.code() == 500) {
                    YomicepaExceptions.HTTP.InternalServerError(exception.code())
                } else {
                    YomicepaExceptions.HTTP.UnknownHttpError(exception.code())
                }
            }

            is IOException -> YomicepaExceptions.IOException()
            else -> YomicepaExceptions.UnknownException
        }
    }
}