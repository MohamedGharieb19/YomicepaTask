package com.gharieb.yomicepatask.domain.repo.main

import com.gharieb.yomicepatask.core.handles.TaskResult
import com.gharieb.yomicepatask.domain.entity.main.items.AddItemRequest
import com.gharieb.yomicepatask.domain.entity.main.items.ItemsResponseItem
import com.gharieb.yomicepatask.domain.entity.main.items.SuccessResponse
import com.gharieb.yomicepatask.domain.entity.main.returnRequests.CreateReturnRequestRequest
import com.gharieb.yomicepatask.domain.entity.main.returnRequests.CreateReturnRequestResponse
import com.gharieb.yomicepatask.domain.entity.main.returnRequests.ReturnRequestsResponse
import com.gharieb.yomicepatask.domain.entity.main.wholesalers.WholesalersResponseItem
import kotlinx.coroutines.flow.Flow

interface ReturnRequestsRepo {
    suspend fun getWholesalers(pharmacyId: Int): Flow<TaskResult<List<WholesalersResponseItem>>>
    suspend fun getReturnRequests(pharmacyId: Int): Flow<TaskResult<ReturnRequestsResponse>>
    suspend fun createReturnRequest(
        pharmacyId: Int,
        createReturnRequestRequest: CreateReturnRequestRequest
    ): Flow<TaskResult<CreateReturnRequestResponse>>

    suspend fun addItemsToReturnRequest(
        pharmacyId: Int,
        returnRequestId: Int,
        addItemRequest: AddItemRequest
    ): Flow<TaskResult<SuccessResponse>>

    suspend fun editItemsOfReturnRequest(
        pharmacyId: Int,
        returnRequestId: Int,
        itemId: Int,
        addItemRequest: AddItemRequest
    ): Flow<TaskResult<SuccessResponse>>

    suspend fun getItemsOfReturnRequest(
        pharmacyId: Int,
        returnRequestId: Int
    ): Flow<TaskResult<List<ItemsResponseItem>>>

    suspend fun deleteItemOfReturnRequest(
        pharmacyId: Int,
        returnRequestId: Int,
        itemId: Int
    ): Flow<TaskResult<SuccessResponse>>

}