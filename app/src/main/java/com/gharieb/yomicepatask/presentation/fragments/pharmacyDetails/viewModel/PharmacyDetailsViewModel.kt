package com.gharieb.yomicepatask.presentation.fragments.pharmacyDetails.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gharieb.yomicepatask.core.handles.TaskResult
import com.gharieb.yomicepatask.domain.entity.main.pharmacy.PharmacyResponse
import com.gharieb.yomicepatask.domain.entity.main.returnRequests.ReturnRequestsResponse
import com.gharieb.yomicepatask.domain.entity.main.wholesalers.WholesalersResponseItem
import com.gharieb.yomicepatask.domain.useCase.main.pharmacy.PharmacyDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PharmacyDetailsViewModel @Inject constructor(
    private val pharmacyDetailsUseCase: PharmacyDetailsUseCase
) : ViewModel() {

    private val _getPharmacyState = MutableStateFlow<TaskResult<PharmacyResponse>?>(TaskResult.Loading)
    val getPharmacyState = _getPharmacyState.asStateFlow()

    private val _getReturnRequestsState = MutableStateFlow<TaskResult<ReturnRequestsResponse>?>(TaskResult.Loading)
    val getReturnRequestsState = _getReturnRequestsState.asStateFlow()

    private val _getWholesalersState = MutableStateFlow<TaskResult<List<WholesalersResponseItem>>?>(TaskResult.Loading)
    val getWholesalersState = _getWholesalersState.asStateFlow()

    fun getPharmacy(pharmacyId: Int) {
        viewModelScope.launch {
            pharmacyDetailsUseCase.getPharmacyUseCase(pharmacyId = pharmacyId).collect {
                _getPharmacyState.value = it
            }
        }
    }

    fun getReturnRequests(pharmacyId: Int) {
        viewModelScope.launch {
            pharmacyDetailsUseCase.getReturnRequestsUseCase(pharmacyId = pharmacyId).collect {
                _getReturnRequestsState.value = it
            }
        }
    }

    fun getWholesalers(pharmacyId: Int) {
        viewModelScope.launch {
            pharmacyDetailsUseCase.wholesalersUseCase(pharmacyId = pharmacyId).collect {
                _getWholesalersState.value = it
            }
        }
    }

}