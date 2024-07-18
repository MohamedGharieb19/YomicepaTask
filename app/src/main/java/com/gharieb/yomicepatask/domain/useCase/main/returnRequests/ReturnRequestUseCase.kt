package com.gharieb.yomicepatask.domain.useCase.main.returnRequests
data class ReturnRequestUseCase(
    val createReturnRequestUseCase: CreateReturnRequestsUseCase,
    val wholesalersUseCase: GetWholesalersUseCase
)