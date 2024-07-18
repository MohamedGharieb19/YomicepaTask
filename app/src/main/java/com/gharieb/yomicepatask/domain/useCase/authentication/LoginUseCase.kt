package com.gharieb.yomicepatask.domain.useCase.authentication

import com.gharieb.yomicepatask.domain.repo.authentication.LoginRepo

class LoginUseCase(private val loginRepo: LoginRepo) {
    suspend operator fun invoke(userName: String, password: String) =
        loginRepo.login(userName = userName, password = password)

}