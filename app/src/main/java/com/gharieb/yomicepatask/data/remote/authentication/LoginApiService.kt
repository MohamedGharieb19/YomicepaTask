package com.gharieb.yomicepatask.data.remote.authentication

import com.gharieb.yomicepatask.core.common.constants.CONSTANTS.LOGIN
import com.gharieb.yomicepatask.domain.entity.authentication.LoginRequest
import com.gharieb.yomicepatask.domain.entity.authentication.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {
    @POST(LOGIN)
    suspend fun login(
        @Body loginRequest: LoginRequest
    ) : Response<LoginResponse>

}