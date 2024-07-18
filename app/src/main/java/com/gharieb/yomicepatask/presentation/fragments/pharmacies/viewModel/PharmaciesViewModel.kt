package com.gharieb.yomicepatask.presentation.fragments.pharmacies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gharieb.yomicepatask.core.handles.TaskResult
import com.gharieb.yomicepatask.domain.entity.main.pharmacies.PharmaciesItem
import com.gharieb.yomicepatask.domain.useCase.main.pharmacy.GetPharmaciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PharmaciesViewModel @Inject constructor(
    private val getPharmaciesUseCase: GetPharmaciesUseCase,
) : ViewModel() {

    private val _getPharmaciesState = MutableStateFlow<TaskResult<List<PharmaciesItem>>?>(TaskResult.Loading)
    val getPharmaciesState = _getPharmaciesState.asStateFlow()

    init {
        getPharmacies()
    }

    private fun getPharmacies() {
        viewModelScope.launch {
            getPharmaciesUseCase().collect {
                _getPharmaciesState.value = it
            }
        }
    }

}