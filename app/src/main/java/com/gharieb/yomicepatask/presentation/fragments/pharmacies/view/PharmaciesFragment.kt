package com.gharieb.yomicepatask.presentation.fragments.pharmacies.view

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
import com.gharieb.yomicepatask.core.common.listeners.ClickListener
import com.gharieb.yomicepatask.core.common.dialogs.alertDialogProgressBar
import com.gharieb.yomicepatask.core.handles.HandleStates.handleErrorResult
import com.gharieb.yomicepatask.core.handles.HandleStates.handleLoadingState
import com.gharieb.yomicepatask.core.handles.TaskResult
import com.gharieb.yomicepatask.databinding.FragmentPharmaciesBinding
import com.gharieb.yomicepatask.domain.entity.main.pharmacies.PharmaciesItem
import com.gharieb.yomicepatask.presentation.fragments.pharmacies.adapter.PharmaciesAdapter
import com.gharieb.yomicepatask.presentation.fragments.pharmacies.viewModel.PharmaciesViewModel
import com.gharieb.yomicepatask.presentation.sharedViewModel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PharmaciesFragment : Fragment(), ClickListener {
    private var _binding: FragmentPharmaciesBinding? = null
    private val binding get() = _binding!!
    private lateinit var pharmaciesAdapter: PharmaciesAdapter
    private val viewModel: PharmaciesViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPharmaciesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        observerGetPharmacies()
    }

    private fun observerGetPharmacies() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPharmaciesState.collect { result ->
                result?.let {
                    when (result) {
                        is TaskResult.Success -> handleGetPharmaciesSuccessResult(result.value)
                        is TaskResult.Error -> handleErrorResult(
                            errorMessage = result.exception.message,
                            context = requireContext()
                        )

                        is TaskResult.Loading -> handleLoadingState(context = requireContext())
                    }
                }
            }
        }
    }

    private fun handleGetPharmaciesSuccessResult(pharmaciesResponse: List<PharmaciesItem>) {
        alertDialogProgressBar?.dismiss()
        pharmaciesAdapter.differ.submitList(pharmaciesResponse)
    }

    private fun setupAdapter() {
        pharmaciesAdapter = PharmaciesAdapter(this)
        binding.pharmaciesRecyclerView.adapter = pharmaciesAdapter
    }

    override fun onItemClick(id: Int) {
        onPharmacyClick(pharmacyId = id)
    }

    private fun onPharmacyClick(pharmacyId: Int) {
        sharedViewModel.setPharmacyId(pharmacyId)
        findNavController().navigate(R.id.action_pharmaciesFragment_to_pharmacyDetailsFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}