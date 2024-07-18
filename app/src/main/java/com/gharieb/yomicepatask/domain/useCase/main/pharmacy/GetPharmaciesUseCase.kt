package com.gharieb.yomicepatask.domain.useCase.main.pharmacy

import com.gharieb.yomicepatask.domain.repo.main.PharmaciesRepo

class GetPharmaciesUseCase(private val pharmaciesRepo: PharmaciesRepo) {
    suspend operator fun invoke() = pharmaciesRepo.getPharmacies()

}