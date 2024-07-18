package com.gharieb.yomicepatask.data.repo.main

import com.gharieb.yomicepatask.core.common.constants.CONSTANTS.TOKEN
import com.gharieb.yomicepatask.core.common.sharedPreference.SharedPreferencesHelper
import com.gharieb.yomicepatask.core.exceptions.ExceptionMapper
import com.gharieb.yomicepatask.core.handles.HandleResponse
import com.gharieb.yomicepatask.core.handles.TaskResult
import com.gharieb.yomicepatask.data.remote.main.ReturnRequestsApiService
import com.gharieb.yomicepatask.domain.entity.main.items.AddItemRequest
import com.gharieb.yomicepatask.domain.entity.main.items.ItemsResponseItem
import com.gharieb.yomicepatask.domain.entity.main.items.SuccessResponse
import com.gharieb.yomicepatask.domain.entity.main.returnRequests.CreateReturnRequestRequest
import com.gharieb.yomicepatask.domain.entity.main.returnRequests.CreateReturnRequestResponse
import com.gharieb.yomicepatask.domain.entity.main.returnRequests.ReturnRequestsResponse
import com.gharieb.yomicepatask.domain.entity.main.wholesalers.WholesalersResponseItem
import com.gharieb.yomicepatask.domain.repo.main.ReturnRequestsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ReturnRequestsRepoImpl(
    private val returnRequestsApiService: ReturnRequestsApiService,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : ReturnRequestsRepo {
    override suspend fun getWholesalers(pharmacyId: Int): Flow<TaskResult<List<WholesalersResponseItem>>> = flow {
        try {
            val response = returnRequestsApiService.getWholesalers(pharmacyId = pharmacyId, token = "Bearer ${sharedPreferencesHelper.get<String>(TOKEN)}")
            emit(HandleResponse.handleResponseFlow(response))
        } catch (e: Exception) {
            emit(TaskResult.Error(exception = ExceptionMapper.mapExceptionToSolutionXException(e)))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getReturnRequests(pharmacyId: Int): Flow<TaskResult<ReturnRequestsResponse>> = flow {
        try {
            val response = returnRequestsApiService.getReturnRequests(pharmacyId = pharmacyId, token = "Bearer ${sharedPreferencesHelper.get<String>(TOKEN)}")
            emit(HandleResponse.handleResponseFlow(response))
        } catch (e: Exception) {
            emit(TaskResult.Error(exception = ExceptionMapper.mapExceptionToSolutionXException(e)))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun createReturnRequest(
        pharmacyId: Int,
        createReturnRequestRequest: CreateReturnRequestRequest
    ): Flow<TaskResult<CreateReturnRequestResponse>> = flow {
        try {
            val response = returnRequestsApiService.createReturnRequest(createReturnRequest = createReturnRequestRequest,pharmacyId = pharmacyId, token = "Bearer ${sharedPreferencesHelper.get<String>(TOKEN)}")
            emit(HandleResponse.handleResponseFlow(response))
        } catch (e: Exception) {
            emit(TaskResult.Error(exception = ExceptionMapper.mapExceptionToSolutionXException(e)))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun addItemsToReturnRequest(
        pharmacyId: Int,
        returnRequestId: Int,
        addItemRequest: AddItemRequest
    ): Flow<TaskResult<SuccessResponse>> = flow {
        try {
            emit(TaskResult.Loading)
            val response = returnRequestsApiService.addItemsToReturnRequest(addItemRequest = addItemRequest, returnRequestId = returnRequestId,pharmacyId = pharmacyId, token = "Bearer ${sharedPreferencesHelper.get<String>(TOKEN)}")
            emit(HandleResponse.handleResponseFlow(response))
        } catch (e: Exception) {
            emit(TaskResult.Error(exception = ExceptionMapper.mapExceptionToSolutionXException(e)))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun editItemsOfReturnRequest(
        pharmacyId: Int,
        returnRequestId: Int,
        itemId: Int,
        addItemRequest: AddItemRequest
    ): Flow<TaskResult<SuccessResponse>> = flow {
        try {
            emit(TaskResult.Loading)
            val response = returnRequestsApiService.editItemOfReturnRequest(itemId = itemId,addItemRequest = addItemRequest, returnRequestId = returnRequestId,pharmacyId = pharmacyId, token = "Bearer ${sharedPreferencesHelper.get<String>(TOKEN)}")
            emit(HandleResponse.handleResponseFlow(response))
        } catch (e: Exception) {
            emit(TaskResult.Error(exception = ExceptionMapper.mapExceptionToSolutionXException(e)))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getItemsOfReturnRequest(
        pharmacyId: Int,
        returnRequestId: Int
    ): Flow<TaskResult<List<ItemsResponseItem>>> = flow {
        try {
            val response = returnRequestsApiService.getItemsOfReturnRequest(returnRequestId = returnRequestId,pharmacyId = pharmacyId, token = "Bearer ${sharedPreferencesHelper.get<String>(TOKEN)}")
            emit(HandleResponse.handleResponseFlow(response))
        } catch (e: Exception) {
            emit(TaskResult.Error(exception = ExceptionMapper.mapExceptionToSolutionXException(e)))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteItemOfReturnRequest(
        pharmacyId: Int,
        returnRequestId: Int,
        itemId: Int
    ): Flow<TaskResult<SuccessResponse>> = flow {
        try {
            emit(TaskResult.Loading)
            val response = returnRequestsApiService.deleteItemOfReturnRequest(itemId = itemId,returnRequestId = returnRequestId,pharmacyId = pharmacyId, token = "Bearer ${sharedPreferencesHelper.get<String>(TOKEN)}")
            emit(HandleResponse.handleResponseFlow(response))
        } catch (e: Exception) {
            emit(TaskResult.Error(exception = ExceptionMapper.mapExceptionToSolutionXException(e)))
        }
    }.flowOn(Dispatchers.IO)


}