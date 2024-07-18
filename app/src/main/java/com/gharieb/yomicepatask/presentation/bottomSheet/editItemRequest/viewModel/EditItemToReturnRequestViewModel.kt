package com.gharieb.yomicepatask.presentation.bottomSheet.editItemRequest.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gharieb.yomicepatask.core.handles.TaskResult
import com.gharieb.yomicepatask.domain.entity.main.items.AddItemRequest
import com.gharieb.yomicepatask.domain.entity.main.items.SuccessResponse
import com.gharieb.yomicepatask.domain.useCase.main.returnRequests.items.EditItemOfReturnRequestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditItemToReturnRequestViewModel @Inject constructor(
    private val editItemToReturnRequestUseCase: EditItemOfReturnRequestUseCase
) : ViewModel() {

    private val _editItemRequestState = MutableStateFlow<TaskResult<SuccessResponse>?>(null)
    val editItemRequestState = _editItemRequestState.asStateFlow()
    fun editItemToReturnRequest(
        itemId: Int,
        pharmacyId: Int,
        returnRequestId: Int,
        editItemRequest: AddItemRequest
    ) {
        viewModelScope.launch {
            editItemToReturnRequestUseCase(
                itemId = itemId,
                pharmacyId = pharmacyId,
                returnRequestId = returnRequestId,
                addItemRequest = editItemRequest
            ).collect {
                _editItemRequestState.value = it
            }
        }
    }

}