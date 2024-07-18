package com.gharieb.yomicepatask.data.remote.main

import com.gharieb.yomicepatask.core.common.constants.CONSTANTS
import com.gharieb.yomicepatask.core.common.constants.CONSTANTS.GET_PHARMACY
import com.gharieb.yomicepatask.core.common.constants.CONSTANTS.ITEMS
import com.gharieb.yomicepatask.core.common.constants.CONSTANTS.RETURN_REQUESTS
import com.gharieb.yomicepatask.domain.entity.main.items.AddItemRequest
import com.gharieb.yomicepatask.domain.entity.main.items.SuccessResponse
import com.gharieb.yomicepatask.domain.entity.main.items.ItemsResponseItem
import com.gharieb.yomicepatask.domain.entity.main.returnRequests.CreateReturnRequestRequest
import com.gharieb.yomicepatask.domain.entity.main.returnRequests.CreateReturnRequestResponse
import com.gharieb.yomicepatask.domain.entity.main.returnRequests.ReturnRequestsResponse
import com.gharieb.yomicepatask.domain.entity.main.wholesalers.WholesalersResponseItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ReturnRequestsApiService {
    @GET("$GET_PHARMACY{id}${CONSTANTS.LIST_OF_WHOLESALERS}")
    suspend fun getWholesalers(
        @Header("Authorization") token: String,
        @Path("id") pharmacyId: Int
    ) : Response<List<WholesalersResponseItem>>
    @GET("$GET_PHARMACY{id}$RETURN_REQUESTS")
    suspend fun getReturnRequests(
        @Header("Authorization")  token:String,
        @Path("id") pharmacyId:Int
    ): Response<ReturnRequestsResponse>

    @POST("$GET_PHARMACY{id}$RETURN_REQUESTS")
    suspend fun createReturnRequest(
        @Header("Authorization")  token:String,
        @Path("id") pharmacyId:Int,
        @Body createReturnRequest: CreateReturnRequestRequest
    ): Response<CreateReturnRequestResponse>

    @POST("$GET_PHARMACY{pharmacyId}$RETURN_REQUESTS/{returnRequestId}$ITEMS")
    suspend fun addItemsToReturnRequest(
        @Header("Authorization")  token:String,
        @Path("pharmacyId") pharmacyId:Int,
        @Path("returnRequestId") returnRequestId:Int,
        @Body addItemRequest: AddItemRequest
    ): Response<SuccessResponse>

    @PUT("$GET_PHARMACY{pharmacyId}$RETURN_REQUESTS/{returnRequestId}$ITEMS/{itemId}")
    suspend fun editItemOfReturnRequest(
        @Header("Authorization")  token:String,
        @Path("pharmacyId") pharmacyId:Int,
        @Path("returnRequestId") returnRequestId:Int,
        @Path("itemId") itemId:Int,
        @Body addItemRequest: AddItemRequest
    ): Response<SuccessResponse>

    @GET("$GET_PHARMACY{pharmacyId}$RETURN_REQUESTS/{returnRequestId}$ITEMS")
    suspend fun getItemsOfReturnRequest(
        @Header("Authorization")  token:String,
        @Path("pharmacyId") pharmacyId:Int,
        @Path("returnRequestId") returnRequestId:Int,
    ): Response<List<ItemsResponseItem>>

    @DELETE("$GET_PHARMACY{pharmacyId}$RETURN_REQUESTS/{returnRequestId}$ITEMS/{itemId}")
    suspend fun deleteItemOfReturnRequest(
        @Header("Authorization")  token:String,
        @Path("pharmacyId") pharmacyId:Int,
        @Path("returnRequestId") returnRequestId:Int,
        @Path("itemId") itemId:Int,
    ): Response<SuccessResponse>

}