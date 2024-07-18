package com.gharieb.yomicepatask.domain.useCase.main.returnRequests.items

import com.gharieb.yomicepatask.domain.entity.main.items.AddItemRequest
import com.gharieb.yomicepatask.domain.repo.main.ReturnRequestsRepo

class EditItemOfReturnRequestUseCase(private val returnRequestsRepo: ReturnRequestsRepo) {
    suspend operator fun invoke(
        itemId: Int,
        pharmacyId: Int,
        returnRequestId: Int,
        addItemRequest: AddItemRequest
    ) = returnRequestsRepo.editItemsOfReturnRequest(
        itemId = itemId,
        pharmacyId = pharmacyId,
        returnRequestId = returnRequestId,
        addItemRequest = addItemRequest
    )

}