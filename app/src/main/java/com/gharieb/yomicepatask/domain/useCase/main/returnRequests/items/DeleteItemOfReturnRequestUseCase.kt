package com.gharieb.yomicepatask.domain.useCase.main.returnRequests.items

import com.gharieb.yomicepatask.domain.repo.main.ReturnRequestsRepo

class DeleteItemOfReturnRequestUseCase(private val returnRequestsRepo: ReturnRequestsRepo) {
    suspend operator fun invoke(
        pharmacyId: Int,
        returnRequestId: Int,
        itemId: Int
    ) = returnRequestsRepo.deleteItemOfReturnRequest(
        pharmacyId = pharmacyId,
        returnRequestId = returnRequestId,
        itemId = itemId
    )

}