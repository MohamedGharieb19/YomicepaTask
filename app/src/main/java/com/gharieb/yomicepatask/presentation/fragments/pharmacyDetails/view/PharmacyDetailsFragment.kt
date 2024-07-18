package com.gharieb.yomicepatask.presentation.fragments.pharmacyDetails.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.gharieb.yomicepatask.R
import com.gharieb.yomicepatask.core.common.dialogs.alertDialogProgressBar
import com.gharieb.yomicepatask.core.handles.HandleStates
import com.gharieb.yomicepatask.core.handles.TaskResult
import com.gharieb.yomicepatask.databinding.FragmentPharmacyDetailsBinding
import com.gharieb.yomicepatask.domain.entity.main.pharmacy.PharmacyResponse
import com.gharieb.yomicepatask.domain.entity.main.returnRequests.ReturnRequestsResponse
import com.gharieb.yomicepatask.domain.entity.main.wholesalers.WholesalersResponseItem
import com.gharieb.yomicepatask.presentation.fragments.pharmacyDetails.adapter.ReturnRequestsAdapter
import com.gharieb.yomicepatask.presentation.fragments.pharmacyDetails.viewModel.PharmacyDetailsViewModel
import com.gharieb.yomicepatask.presentation.sharedViewModel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PharmacyDetailsFragment : Fragment() {
    private var _binding: FragmentPharmacyDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PharmacyDetailsViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var returnRequestsAdapter: ReturnRequestsAdapter
    private var wholesaler = StringBuilder()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPharmacyDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerGetWholesalers()
        observerGetPharmacy()
        actions()
    }

    private fun actions() {
        binding.apply {
            createReturnRequestFab.setOnClickListener {
                findNavController().navigate(R.id.action_pharmacyDetailsFragment_to_createReturnRequestBottomSheet)
            }
        }
    }

    private fun observerGetPharmacy() {
        viewModel.getPharmacy(sharedViewModel.getPharmacyId().value!!)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPharmacyState.collect { result ->
                result?.let {
                    when (result) {
                        is TaskResult.Success -> handleGetPharmacyDetailsSuccessResult(result.value)
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

    private fun observerGetWholesalers() {
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
        wholesalersResponseItems.forEach {
                wholesaler.append(it.name)
        }
        setupAdapter()
        observerReturnRequests()
    }

    private fun observerReturnRequests() {
        viewModel.getReturnRequests(sharedViewModel.getPharmacyId().value!!)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getReturnRequestsState.collect { result ->
                result?.let {
                    when (result) {
                        is TaskResult.Success -> handleGetReturnRequestsSuccessResult(result.value)
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

    private fun handleGetReturnRequestsSuccessResult(returnRequestsResponses: ReturnRequestsResponse) {
        alertDialogProgressBar?.dismiss()
        returnRequestsAdapter.differ.submitList(returnRequestsResponses.content)
    }

    private fun handleGetPharmacyDetailsSuccessResult(pharmaciesResponse: PharmacyResponse) {
        alertDialogProgressBar?.dismiss()
        binding.apply {
            pharmacyNameText.text = pharmaciesResponse.pharmacy?.doingBusinessAs
        }

    }

    private fun setupAdapter() {
        returnRequestsAdapter = ReturnRequestsAdapter(wholesalers = wholesaler.toString())
        binding.returnRequestsRecyclerView.adapter = returnRequestsAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}