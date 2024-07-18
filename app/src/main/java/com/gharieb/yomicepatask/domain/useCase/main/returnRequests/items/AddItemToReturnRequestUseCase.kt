package com.gharieb.yomicepatask.domain.useCase.main.returnRequests.items

import com.gharieb.yomicepatask.domain.entity.main.items.AddItemRequest
import com.gharieb.yomicepatask.domain.repo.main.ReturnRequestsRepo

class AddItemToReturnRequestUseCase(private val returnRequestsRepo: ReturnRequestsRepo) {
    suspend operator fun invoke(
        pharmacyId: Int,
        returnRequestId: Int,
        addItemRequest: AddItemRequest
    ) = returnRequestsRepo.addItemsToReturnRequest(
        pharmacyId = pharmacyId,
        returnRequestId = returnRequestId,
        addItemRequest = addItemRequest
    )

}