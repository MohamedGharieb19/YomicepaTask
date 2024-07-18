package com.gharieb.yomicepatask.presentation.fragments.addItem.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gharieb.yomicepatask.core.handles.TaskResult
import com.gharieb.yomicepatask.domain.entity.main.items.ItemsResponseItem
import com.gharieb.yomicepatask.domain.entity.main.items.SuccessResponse
import com.gharieb.yomicepatask.domain.useCase.main.returnRequests.items.DeleteItemOfReturnRequestUseCase
import com.gharieb.yomicepatask.domain.useCase.main.returnRequests.items.GetItemsOfReturnRequestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val deleteItemOfReturnRequestUseCase: DeleteItemOfReturnRequestUseCase,
    private val getItemsOfReturnRequestUseCase: GetItemsOfReturnRequestUseCase
) : ViewModel() {

    private val _itemsRequestState = MutableStateFlow<TaskResult<List<ItemsResponseItem>>?>(TaskResult.Loading)
    val itemsRequestState = _itemsRequestState.asStateFlow()

    private val _deleteItemRequestState = MutableStateFlow<TaskResult<SuccessResponse>?>(null)
    val deleteItemRequestState = _deleteItemRequestState.asStateFlow()

    fun itemsToReturnRequest(pharmacyId: Int, returnRequestId: Int, ) {
        viewModelScope.launch {
            getItemsOfReturnRequestUseCase(pharmacyId = pharmacyId, returnRequestId = returnRequestId,).collect {
                _itemsRequestState.value = it
            }
        }
    }
    fun deleteItemToReturnRequest(
        pharmacyId: Int,
        returnRequestId: Int,
        itemId: Int
    ) {
        viewModelScope.launch {
            deleteItemOfReturnRequestUseCase(
                pharmacyId = pharmacyId,
                returnRequestId = returnRequestId,
                itemId = itemId
            ).collect {
                _deleteItemRequestState.value = it
            }
        }
    }

}