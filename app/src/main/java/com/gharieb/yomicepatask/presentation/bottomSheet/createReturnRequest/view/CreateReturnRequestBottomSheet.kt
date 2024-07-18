package com.gharieb.yomicepatask.presentation.bottomSheet.createReturnRequest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.gharieb.yomicepatask.R
import com.gharieb.yomicepatask.core.common.listeners.ClickListener
import com.gharieb.yomicepatask.core.common.dialogs.alertDialogProgressBar
import com.gharieb.yomicepatask.core.common.dialogs.okDialog
import com.gharieb.yomicepatask.core.handles.HandleStates
import com.gharieb.yomicepatask.core.handles.TaskResult
import com.gharieb.yomicepatask.databinding.BottomSheetCreateReturnRequestBinding
import com.gharieb.yomicepatask.domain.entity.main.returnRequests.CreateReturnRequestRequest
import com.gharieb.yomicepatask.domain.entity.main.returnRequests.CreateReturnRequestResponse
import com.gharieb.yomicepatask.domain.entity.main.wholesalers.WholesalersResponseItem
import com.gharieb.yomicepatask.presentation.bottomSheet.createReturnRequest.adapter.WholesalersAdapter
import com.gharieb.yomicepatask.presentation.bottomSheet.createReturnRequest.viewModel.CreateReturnRequestViewModel
import com.gharieb.yomicepatask.presentation.sharedViewModel.SharedViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateReturnRequestBottomSheet : BottomSheetDialogFragment(), ClickListener {
    private var _binding: BottomSheetCreateReturnRequestBinding? = null
    private val binding get() = _binding!!
    private lateinit var wholesalersAdapter: WholesalersAdapter
    private val viewModel: CreateReturnRequestViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var wholesalersId: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = BottomSheetCreateReturnRequestBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        observerWholesalers()
        observerCreateReturnRequest()
        actions()
    }

    private fun actions() {
        binding.apply {
            confirmBtn.setOnClickListener {
                if (getServiceType().isEmpty() || wholesalersId == null){
                    okDialog(getString(R.string.please_fill_all_fields),requireContext())
                }else {
                    viewModel.createReturnRequest(
                        pharmacyId = sharedViewModel.getPharmacyId().value!!,
                        createReturnRequestRequest = CreateReturnRequestRequest(
                            serviceType = getServiceType(),
                            wholesalerId = wholesalersId
                        )
                    )
                }
            }
        }
    }

    private fun getServiceType(): String {
        binding.apply {
            return when (radioGroup.checkedRadioButtonId) {
                fullServiceOption.id -> requireContext().getString(R.string.full_service)
                expressServiceOption.id -> requireContext().getString(R.string.express_service)
                else -> {""}
            }
        }
    }

    private fun setupAdapter() {
        wholesalersAdapter = WholesalersAdapter(this)
        binding.wholesalersRecyclerView.adapter = wholesalersAdapter
    }


    private fun observerCreateReturnRequest() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.createReturnRequestState.collect { result ->
                result?.let {
                    when (result) {
                        is TaskResult.Success -> handleCreateReturnRequestSuccessResult(result.value)
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

    private fun handleCreateReturnRequestSuccessResult(value: CreateReturnRequestResponse) {
        alertDialogProgressBar?.dismiss()
        sharedViewModel.setReturnRequestId(value.id!!)
        findNavController().navigate(R.id.action_createReturnRequestBottomSheet_to_addItemFragment)
    }

    private fun observerWholesalers() {
        viewModel.getWholesalers(sharedViewModel.getPharmacyId().value!!)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getWholesalersState.collect { result ->
                result?.let {
                    when (result) {
                        is TaskResult.Success -> handleGetWholesalersSuccessResult(result.value)
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

    private fun handleGetWholesalersSuccessResult(wholesalersResponseItems: List<WholesalersResponseItem>) {
        alertDialogProgressBar?.dismiss()
        wholesalersAdapter.differ.submitList(wholesalersResponseItems)
    }

    override fun onItemClick(id: Int) {
        wholesalersId = id
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}