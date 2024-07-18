package com.gharieb.yomicepatask.core.di.useCaseModule

import com.gharieb.yomicepatask.domain.repo.authentication.LoginRepo
import com.gharieb.yomicepatask.domain.useCase.authentication.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LoginUseCaseModule {

    @Provides
    fun provideLoginUseCase(loginRepo: LoginRepo): LoginUseCase {
        return LoginUseCase(loginRepo)
    }
}