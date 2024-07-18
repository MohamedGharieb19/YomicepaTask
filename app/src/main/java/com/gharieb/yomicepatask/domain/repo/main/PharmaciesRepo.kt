package com.gharieb.yomicepatask.domain.repo.main

import com.gharieb.yomicepatask.core.handles.TaskResult
import com.gharieb.yomicepatask.domain.entity.main.pharmacies.PharmaciesItem
import com.gharieb.yomicepatask.domain.entity.main.pharmacy.PharmacyResponse
import kotlinx.coroutines.flow.Flow

interface PharmaciesRepo {
    suspend fun getPharmacies(): Flow<TaskResult<List<PharmaciesItem>>>
    suspend fun getPharmacy(pharmacyId: Int): Flow<TaskResult<PharmacyResponse>>
}