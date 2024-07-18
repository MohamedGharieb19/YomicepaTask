package com.gharieb.yomicepatask.presentation.bottomSheet.createReturnRequest.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gharieb.yomicepatask.core.handles.TaskResult
import com.gharieb.yomicepatask.domain.entity.main.returnRequests.CreateReturnRequestRequest
import com.gharieb.yomicepatask.domain.entity.main.returnRequests.CreateReturnRequestResponse
import com.gharieb.yomicepatask.domain.entity.main.wholesalers.WholesalersResponseItem
import com.gharieb.yomicepatask.domain.useCase.main.returnRequests.ReturnRequestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateReturnRequestViewModel @Inject constructor(
    private val returnRequestUseCase: ReturnRequestUseCase
) : ViewModel() {

    private val _getWholesalersState =
        MutableStateFlow<TaskResult<List<WholesalersResponseItem>>?>(TaskResult.Loading)
    val getWholesalersState = _getWholesalersState.asStateFlow()

    private val _createReturnRequestState =
        MutableStateFlow<TaskResult<CreateReturnRequestResponse>?>(TaskResult.Loading)
    val createReturnRequestState = _createReturnRequestState.asStateFlow()

    fun getWholesalers(pharmacyId: Int) {
        viewModelScope.launch {
            returnRequestUseCase.wholesalersUseCase(pharmacyId = pharmacyId).collect {
                _getWholesalersState.value = it
            }
        }
    }

    fun createReturnRequest(
        pharmacyId: Int,
        createReturnRequestRequest: CreateReturnRequestRequest
    ) {
        viewModelScope.launch {
            returnRequestUseCase.createReturnRequestUseCase(
                pharmacyId = pharmacyId,
                createReturnRequestRequest = createReturnRequestRequest
            ).collect {
                _createReturnRequestState.value = it
            }
        }
    }

}