package com.gharieb.yomicepatask.presentation.bottomSheet.editItemRequest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.gharieb.yomicepatask.R
import com.gharieb.yomicepatask.core.common.dialogs.alertDialogProgressBar
import com.gharieb.yomicepatask.core.common.dialogs.okDialog
import com.gharieb.yomicepatask.core.handles.HandleStates
import com.gharieb.yomicepatask.core.handles.TaskResult
import com.gharieb.yomicepatask.databinding.BottomSheetEditItemBinding
import com.gharieb.yomicepatask.domain.entity.main.items.AddItemRequest
import com.gharieb.yomicepatask.domain.entity.main.items.ItemsResponseItem
import com.gharieb.yomicepatask.domain.entity.main.items.SuccessResponse
import com.gharieb.yomicepatask.presentation.bottomSheet.editItemRequest.viewModel.EditItemToReturnRequestViewModel
import com.gharieb.yomicepatask.presentation.sharedViewModel.SharedViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditItemBottomSheet : BottomSheetDialogFragment() {
    private var _binding: BottomSheetEditItemBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EditItemToReturnRequestViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var item: ItemsResponseItem? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = BottomSheetEditItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        actions()
        editItemObserver()
    }

    private fun editItemObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.editItemRequestState.collect { result ->
                result?.let {
                    when (result) {
                        is TaskResult.Success -> handleEditItemSuccessResult(result.value)
                        is TaskResult.Error -> HandleStates.handleErrorResult(
                            errorMessage = result.exception.message,
                            context = requireContext()
                        )

                        is TaskResult.Loading -> HandleStates.handleLoadingState(context = requireContext())
                    }
                }
            }
        }
    }

    private fun handleEditItemSuccessResult(successResponse: SuccessResponse) {
        alertDialogProgressBar?.dismiss()
        okDialog(message = successResponse.message, context = requireContext())
    }

    private fun actions() {
        binding.apply {
            saveBtn.setOnClickListener {
                if (descriptionEditText.text.toString().isEmpty()) {
                    okDialog(
                        message = getString(R.string.description_can_t_be_empty),
                        context = requireContext()
                    )
                } else viewModel.editItemToReturnRequest(
                    itemId = item?.id!!,
                    pharmacyId = sharedViewModel.getPharmacyId().value!!,
                    returnRequestId = sharedViewModel.getReturnRequestId().value!!,
                    editItemRequest = editItemRequest()
                )
            }
        }
    }

    private fun getData() {
        sharedViewModel.apply {
            getItemReturnRequest().observe(viewLifecycleOwner) {
                item = it
            }
        }
    }
    private fun editItemRequest(): AddItemRequest {
        return AddItemRequest(
            dosage = item?.dosage,
            requestType = item?.requestType,
            strength = item?.strength,
            description = binding.descriptionEditText.text.toString(),
            packageSize = item?.packageSize,
            lotNumber = item?.lotNumber,
            ndc = item?.ndc,
            partialQuantity = item?.partialQuantity.toString(),
            manufacturer = item?.manufacturer,
            fullQuantity = item?.fullQuantity.toString(),
            name = item?.name,
            expirationDate = item?.expirationDate,
            status = item?.status,
        )
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}