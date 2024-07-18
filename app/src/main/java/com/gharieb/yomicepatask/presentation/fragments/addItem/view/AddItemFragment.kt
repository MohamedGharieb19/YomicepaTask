package com.gharieb.yomicepatask.presentation.fragments.addItem.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.gharieb.yomicepatask.R
import com.gharieb.yomicepatask.core.common.extensionsFunctions.ExtensionsFunctions.hasNullProperty
import com.gharieb.yomicepatask.core.common.dialogs.alertDialogProgressBar
import com.gharieb.yomicepatask.core.common.dialogs.okDialog
import com.gharieb.yomicepatask.core.handles.HandleStates
import com.gharieb.yomicepatask.core.handles.TaskResult
import com.gharieb.yomicepatask.databinding.FragmentAddItemBinding
import com.gharieb.yomicepatask.domain.entity.main.items.AddItemRequest
import com.gharieb.yomicepatask.domain.entity.main.items.SuccessResponse
import com.gharieb.yomicepatask.presentation.fragments.addItem.viewModel.AddItemToReturnRequestViewModel
import com.gharieb.yomicepatask.presentation.sharedViewModel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddItemFragment : Fragment() {
    private var _binding: FragmentAddItemBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddItemToReturnRequestViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actions()
        addItemObserver()
    }

    private fun actions() {
        binding.apply {
            saveBtn.setOnClickListener {
                if (hasNullProperty(addItemRequest())) {
                    okDialog(
                        context = requireContext(),
                        message = getString(R.string.please_fill_all_the_fields)
                    )
                } else viewModel.addItemToReturnRequest(
                    addItemRequest = addItemRequest(),
                    pharmacyId = sharedViewModel.getPharmacyId().value!!,
                    returnRequestId = sharedViewModel.getReturnRequestId().value!!
                )
            }
            goToItemsBtn.setOnClickListener {
                findNavController().navigate(R.id.action_addItemFragment_to_itemsFragment)
            }
        }
    }

    private fun addItemRequest(): AddItemRequest {
        binding.apply {
            return AddItemRequest(
                ndc = ndcEditText.text.toString(),
                description = descriptionEditText.text.toString(),
                manufacturer = manufacturerEditText.text.toString(),
                packageSize = packageSizeEditText.text.toString(),
                requestType = requestTypeEditText.text.toString(),
                name = nameEditText.text.toString(),
                strength = strengthEditText.text.toString(),
                dosage = dosageEditText.text.toString(),
                fullQuantity = fullQuantityEditText.text.toString(),
                partialQuantity = partialQuantityEditText.text.toString(),
                expirationDate = expirationDateEditText.text.toString(),
                status = statusEditText.text.toString().uppercase(),
                lotNumber = LotNumberEditText.text.toString(),
            )
        }
    }

    private fun addItemObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.addItemRequestState.collect { result ->
                result?.let {
                    when (result) {
                        is TaskResult.Success -> handleAddItemSuccessResult(result.value)
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

    private fun handleAddItemSuccessResult(successResponse: SuccessResponse) {
        alertDialogProgressBar?.dismiss()
        okDialog(context = requireContext(), message = successResponse.message)
        binding.apply {
            ndcEditText.setText("")
            descriptionEditText.setText("")
            manufacturerEditText.setText("")
            packageSizeEditText.setText("")
            requestTypeEditText.setText("")
            nameEditText.setText("")
            strengthEditText.setText("")
            dosageEditText.setText("")
            fullQuantityEditText.setText("")
            partialQuantityEditText.setText("")
            expirationDateEditText.setText("")
            statusEditText.setText("")
            LotNumberEditText.setText("")
            goToItemsBtn.isVisible = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}