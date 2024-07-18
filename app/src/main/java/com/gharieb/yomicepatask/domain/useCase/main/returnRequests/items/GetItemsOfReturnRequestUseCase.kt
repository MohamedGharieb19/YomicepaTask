package com.gharieb.yomicepatask.domain.useCase.main.returnRequests.items

import com.gharieb.yomicepatask.domain.repo.main.ReturnRequestsRepo

class GetItemsOfReturnRequestUseCase(private val returnRequestsRepo: ReturnRequestsRepo) {
    suspend operator fun invoke(pharmacyId: Int, returnRequestId: Int) =
        returnRequestsRepo.getItemsOfReturnRequest(
            pharmacyId = pharmacyId,
            returnRequestId = returnRequestId,
        )
}