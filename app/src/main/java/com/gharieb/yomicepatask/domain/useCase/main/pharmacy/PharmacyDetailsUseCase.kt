package com.gharieb.yomicepatask.domain.useCase.main.pharmacy

import com.gharieb.yomicepatask.domain.useCase.main.returnRequests.GetReturnRequestsUseCase
import com.gharieb.yomicepatask.domain.useCase.main.returnRequests.GetWholesalersUseCase


data class PharmacyDetailsUseCase(
    val getPharmacyUseCase: GetPharmacyUseCase,
    val getReturnRequestsUseCase: GetReturnRequestsUseCase,
    val wholesalersUseCase: GetWholesalersUseCase
)