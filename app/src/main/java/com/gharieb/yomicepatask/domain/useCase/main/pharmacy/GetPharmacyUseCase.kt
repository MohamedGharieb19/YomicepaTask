package com.gharieb.yomicepatask.domain.useCase.main.pharmacy

import com.gharieb.yomicepatask.domain.repo.main.PharmaciesRepo

class GetPharmacyUseCase(private val pharmaciesRepo: PharmaciesRepo) {
    suspend operator fun invoke(pharmacyId: Int) = pharmaciesRepo.getPharmacy(pharmacyId = pharmacyId)

}