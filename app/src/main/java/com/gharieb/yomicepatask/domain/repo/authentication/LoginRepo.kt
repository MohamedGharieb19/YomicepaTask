package com.gharieb.yomicepatask.domain.repo.authentication

import com.gharieb.yomicepatask.core.handles.TaskResult
import com.gharieb.yomicepatask.domain.entity.authentication.LoginResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepo {
    suspend fun login(userName: String,password: String): Flow<TaskResult<LoginResponse>>
}