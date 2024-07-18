package com.gharieb.yomicepatask.domain.useCase.main.returnRequests

import com.gharieb.yomicepatask.domain.entity.main.returnRequests.CreateReturnRequestRequest
import com.gharieb.yomicepatask.domain.repo.main.ReturnRequestsRepo

class CreateReturnRequestsUseCase(private val returnRequestsRepo: ReturnRequestsRepo) {
    suspend operator fun invoke(
        pharmacyId: Int,
        createReturnRequestRequest: CreateReturnRequestRequest
    ) = returnRequestsRepo.createReturnRequest(
        pharmacyId = pharmacyId,
        createReturnRequestRequest = createReturnRequestRequest
    )

}