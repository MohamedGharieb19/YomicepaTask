package com.gharieb.yomicepatask.presentation.fragments.addItem.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gharieb.yomicepatask.core.handles.TaskResult
import com.gharieb.yomicepatask.domain.entity.main.items.AddItemRequest
import com.gharieb.yomicepatask.domain.entity.main.items.SuccessResponse
import com.gharieb.yomicepatask.domain.useCase.main.returnRequests.items.AddItemToReturnRequestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemToReturnRequestViewModel @Inject constructor(
    private val addItemToReturnRequestUseCase: AddItemToReturnRequestUseCase
) : ViewModel() {

    private val _addItemRequestState =
        MutableStateFlow<TaskResult<SuccessResponse>?>(null)
    val addItemRequestState = _addItemRequestState.asStateFlow()
    fun addItemToReturnRequest(
        pharmacyId: Int,
        returnRequestId: Int,
        addItemRequest: AddItemRequest
    ) {
        viewModelScope.launch {
            addItemToReturnRequestUseCase(
                pharmacyId = pharmacyId,
                returnRequestId = returnRequestId,
                addItemRequest = addItemRequest
            ).collect {
                _addItemRequestState.value = it
            }
        }
    }

}