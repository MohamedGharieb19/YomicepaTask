package com.gharieb.yomicepatask.domain.useCase.main.returnRequests

import com.gharieb.yomicepatask.domain.repo.main.ReturnRequestsRepo

class GetReturnRequestsUseCase(private val returnRequestsRepo: ReturnRequestsRepo) {
    suspend operator fun invoke(pharmacyId: Int) = returnRequestsRepo.getReturnRequests(pharmacyId = pharmacyId)

}