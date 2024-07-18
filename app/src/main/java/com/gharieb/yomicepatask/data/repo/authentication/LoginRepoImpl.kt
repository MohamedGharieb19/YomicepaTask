package com.gharieb.yomicepatask.data.repo.authentication

import com.gharieb.yomicepatask.core.common.constants.CONSTANTS.TOKEN
import com.gharieb.yomicepatask.core.common.sharedPreference.SharedPreferencesHelper
import com.gharieb.yomicepatask.core.exceptions.ExceptionMapper
import com.gharieb.yomicepatask.core.handles.HandleResponse
import com.gharieb.yomicepatask.core.handles.TaskResult
import com.gharieb.yomicepatask.data.remote.authentication.LoginApiService
import com.gharieb.yomicepatask.domain.entity.authentication.LoginRequest
import com.gharieb.yomicepatask.domain.entity.authentication.LoginResponse
import com.gharieb.yomicepatask.domain.repo.authentication.LoginRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginRepoImpl(
    private val loginApiService: LoginApiService,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : LoginRepo {

    override suspend fun login(
        userName: String,
        password: String,
    ): Flow<TaskResult<LoginResponse>> = flow {
        try {
            emit(TaskResult.Loading)
            val response = loginApiService.login(LoginRequest(username = userName, password = password))
            if (response.isSuccessful) {
                sharedPreferencesHelper.put(key = TOKEN, `object` = response.body()?.token)
            }
            emit(HandleResponse.handleResponseFlow(response))
        } catch (e: Exception) {
            emit(TaskResult.Error(exception = ExceptionMapper.mapExceptionToSolutionXException(e)))
        }
    }.flowOn(Dispatchers.IO)


}