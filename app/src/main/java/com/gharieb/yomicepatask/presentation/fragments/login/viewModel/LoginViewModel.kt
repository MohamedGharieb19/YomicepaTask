package com.gharieb.yomicepatask.presentation.fragments.login.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gharieb.yomicepatask.core.handles.TaskResult
import com.gharieb.yomicepatask.domain.entity.authentication.LoginResponse
import com.gharieb.yomicepatask.domain.useCase.authentication.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow<TaskResult<LoginResponse>?>(null)
    val loginState = _loginState.asStateFlow()

    fun login(userNane: String, password: String) {
        viewModelScope.launch {
            loginUseCase(userName = userNane, password = password).collect {
                _loginState.value = it
            }
        }
    }

}