package com.gharieb.yomicepatask.data.remote.main

import com.gharieb.yomicepatask.core.common.constants.CONSTANTS.FULL
import com.gharieb.yomicepatask.core.common.constants.CONSTANTS.GET_PHARMACY
import com.gharieb.yomicepatask.core.common.constants.CONSTANTS.LIST_OF_PHARMACIES
import com.gharieb.yomicepatask.domain.entity.main.pharmacies.PharmaciesItem
import com.gharieb.yomicepatask.domain.entity.main.pharmacy.PharmacyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface PharmaciesApiService {
    @GET(LIST_OF_PHARMACIES)
    suspend fun getPharmacies(
        @Header("Authorization") token: String
    ) : Response<List<PharmaciesItem>>

    @GET("$GET_PHARMACY{id}$FULL")
    suspend fun getPharmacy(
        @Header("Authorization") token: String,
        @Path("id") pharmacyId: Int
    ) : Response<PharmacyResponse>
}