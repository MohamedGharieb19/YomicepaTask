package com.gharieb.yomicepatask.domain.useCase.main.returnRequests

import com.gharieb.yomicepatask.domain.repo.main.ReturnRequestsRepo

class GetWholesalersUseCase(private val returnRequestsRepo: ReturnRequestsRepo) {
    suspend operator fun invoke(pharmacyId: Int) = returnRequestsRepo.getWholesalers(pharmacyId = pharmacyId)

}