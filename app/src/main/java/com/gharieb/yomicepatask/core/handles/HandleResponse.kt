package com.gharieb.yomicepatask.core.handles

import com.gharieb.yomicepatask.core.exceptions.ExceptionMapper
import retrofit2.Response

object HandleResponse {
    fun <T> handleResponseFlow(response: Response<T>?): TaskResult<T> {
        return try {
            if (response?.isSuccessful == true) {
                TaskResult.Success(response.body()!!)
            } else {
                TaskResult.Error(ExceptionMapper.mapExceptionToSolutionXException(response as Exception))
            }
        }catch (e: Exception){
            TaskResult.Error(ExceptionMapper.mapExceptionToSolutionXException(e))
        }
    }

}