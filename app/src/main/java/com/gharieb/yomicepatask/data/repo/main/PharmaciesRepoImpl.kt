package com.gharieb.yomicepatask.data.repo.main

import com.gharieb.yomicepatask.core.common.constants.CONSTANTS.TOKEN
import com.gharieb.yomicepatask.core.common.sharedPreference.SharedPreferencesHelper
import com.gharieb.yomicepatask.core.exceptions.ExceptionMapper
import com.gharieb.yomicepatask.core.handles.HandleResponse
import com.gharieb.yomicepatask.core.handles.TaskResult
import com.gharieb.yomicepatask.data.remote.main.PharmaciesApiService
import com.gharieb.yomicepatask.domain.entity.main.pharmacies.PharmaciesItem
import com.gharieb.yomicepatask.domain.entity.main.pharmacy.PharmacyResponse
import com.gharieb.yomicepatask.domain.repo.main.PharmaciesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PharmaciesRepoImpl(
    private val pharmaciesApiService: PharmaciesApiService,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : PharmaciesRepo {
    override suspend fun getPharmacies(): Flow<TaskResult<List<PharmaciesItem>>> = flow {
        try {
            val response = pharmaciesApiService.getPharmacies(token = "Bearer ${sharedPreferencesHelper.get<String>(TOKEN)}")
            emit(HandleResponse.handleResponseFlow(response))
        } catch (e: Exception) {
            emit(TaskResult.Error(exception = ExceptionMapper.mapExceptionToSolutionXException(e)))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getPharmacy(pharmacyId: Int): Flow<TaskResult<PharmacyResponse>> = flow {
        try {
            val response = pharmaciesApiService.getPharmacy(pharmacyId = pharmacyId, token = "Bearer ${sharedPreferencesHelper.get<String>(TOKEN)}")
            emit(HandleResponse.handleResponseFlow(response))
        } catch (e: Exception) {
            emit(TaskResult.Error(exception = ExceptionMapper.mapExceptionToSolutionXException(e)))
        }
    }.flowOn(Dispatchers.IO)



}