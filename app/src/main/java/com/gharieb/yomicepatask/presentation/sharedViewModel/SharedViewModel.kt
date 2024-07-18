package com.gharieb.yomicepatask.presentation.sharedViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gharieb.yomicepatask.domain.entity.main.items.ItemsResponseItem

class SharedViewModel : ViewModel() {

    private var pharmacyId = MutableLiveData<Int>()
    fun setPharmacyId(pharmacyId: Int) {
        this@SharedViewModel.pharmacyId.value = pharmacyId
    }
    fun getPharmacyId() = pharmacyId

    private var returnRequestId = MutableLiveData<Int>()
    fun setReturnRequestId(returnRequestId: Int) {
        this@SharedViewModel.returnRequestId.value = returnRequestId
    }
    fun getReturnRequestId() = returnRequestId

    private var itemReturnRequest = MutableLiveData<ItemsResponseItem>()
    fun setItemReturnRequest(itemReturnRequest: ItemsResponseItem) {
        this@SharedViewModel.itemReturnRequest.value = itemReturnRequest
    }
    fun getItemReturnRequest() = itemReturnRequest

}