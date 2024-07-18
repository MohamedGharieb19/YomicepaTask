package com.gharieb.yomicepatask.core.di.useCaseModule

import com.gharieb.yomicepatask.domain.repo.main.PharmaciesRepo
import com.gharieb.yomicepatask.domain.useCase.main.pharmacy.GetPharmacyUseCase
import com.gharieb.yomicepatask.domain.useCase.main.pharmacy.PharmacyDetailsUseCase
import com.gharieb.yomicepatask.domain.useCase.main.returnRequests.GetReturnRequestsUseCase
import com.gharieb.yomicepatask.domain.useCase.main.returnRequests.GetWholesalersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PharmacyDetailsUseCaseModule {

    @Provides
    fun providePharmacyUseCase(pharmaciesRepo: PharmaciesRepo): GetPharmacyUseCase {
        return GetPharmacyUseCase(pharmaciesRepo)
    }

    @Provides
    fun providePharmacyDetailsUseCase(
        getPharmacyUseCase: GetPharmacyUseCase,
        getReturnRequestsUseCase: GetReturnRequestsUseCase,
        wholesalersUseCase: GetWholesalersUseCase
    ): PharmacyDetailsUseCase {
        return PharmacyDetailsUseCase(
            getPharmacyUseCase = getPharmacyUseCase,
            getReturnRequestsUseCase = getReturnRequestsUseCase,
            wholesalersUseCase = wholesalersUseCase
        )
    }

}