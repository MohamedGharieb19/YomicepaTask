package com.gharieb.yomicepatask.core.di.useCaseModule

import com.gharieb.yomicepatask.domain.repo.main.PharmaciesRepo
import com.gharieb.yomicepatask.domain.useCase.main.pharmacy.GetPharmaciesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PharmaciesUseCaseModule {

    @Provides
    fun providePharmaciesUseCase(pharmaciesRepo: PharmaciesRepo): GetPharmaciesUseCase {
        return GetPharmaciesUseCase(pharmaciesRepo)
    }
}