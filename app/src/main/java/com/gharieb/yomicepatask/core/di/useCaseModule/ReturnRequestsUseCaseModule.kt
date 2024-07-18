package com.gharieb.yomicepatask.core.di.useCaseModule

import com.gharieb.yomicepatask.domain.repo.main.ReturnRequestsRepo
import com.gharieb.yomicepatask.domain.useCase.main.returnRequests.CreateReturnRequestsUseCase
import com.gharieb.yomicepatask.domain.useCase.main.returnRequests.GetReturnRequestsUseCase
import com.gharieb.yomicepatask.domain.useCase.main.returnRequests.GetWholesalersUseCase
import com.gharieb.yomicepatask.domain.useCase.main.returnRequests.ReturnRequestUseCase
import com.gharieb.yomicepatask.domain.useCase.main.returnRequests.items.AddItemToReturnRequestUseCase
import com.gharieb.yomicepatask.domain.useCase.main.returnRequests.items.DeleteItemOfReturnRequestUseCase
import com.gharieb.yomicepatask.domain.useCase.main.returnRequests.items.EditItemOfReturnRequestUseCase
import com.gharieb.yomicepatask.domain.useCase.main.returnRequests.items.GetItemsOfReturnRequestUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ReturnRequestsUseCaseModule {

    @Provides
    fun provideGetReturnRequestsUseCase(returnRequestsRepo: ReturnRequestsRepo): GetReturnRequestsUseCase {
        return GetReturnRequestsUseCase(returnRequestsRepo)
    }

    @Provides
    fun provideGetWholesalersUseCase(returnRequestsRepo: ReturnRequestsRepo): GetWholesalersUseCase {
        return GetWholesalersUseCase(returnRequestsRepo)
    }

    @Provides
    fun provideCreateReturnRequestsUseCase(returnRequestsRepo: ReturnRequestsRepo): CreateReturnRequestsUseCase {
        return CreateReturnRequestsUseCase(returnRequestsRepo)
    }

    @Provides
    fun provideAddItemToReturnRequestUseCase(returnRequestsRepo: ReturnRequestsRepo): AddItemToReturnRequestUseCase {
        return AddItemToReturnRequestUseCase(returnRequestsRepo)
    }

    @Provides
    fun provideDeleteItemToReturnRequestUseCase(returnRequestsRepo: ReturnRequestsRepo): DeleteItemOfReturnRequestUseCase {
        return DeleteItemOfReturnRequestUseCase(returnRequestsRepo)
    }

    @Provides
    fun provideItemsReturnRequestUseCase(returnRequestsRepo: ReturnRequestsRepo): GetItemsOfReturnRequestUseCase {
        return GetItemsOfReturnRequestUseCase(returnRequestsRepo)
    }

    @Provides
    fun provideEditItemReturnRequestUseCase(returnRequestsRepo: ReturnRequestsRepo): EditItemOfReturnRequestUseCase {
        return EditItemOfReturnRequestUseCase(returnRequestsRepo)
    }

    @Provides
    fun provideReturnRequestUseCase(
        createReturnRequestsUseCase: CreateReturnRequestsUseCase,
        getWholesalersUseCase: GetWholesalersUseCase
    ): ReturnRequestUseCase {
        return ReturnRequestUseCase(
            createReturnRequestUseCase = createReturnRequestsUseCase,
            wholesalersUseCase = getWholesalersUseCase
        )
    }
}