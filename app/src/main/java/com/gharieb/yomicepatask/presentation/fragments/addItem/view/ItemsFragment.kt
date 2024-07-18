package com.gharieb.yomicepatask.presentation.fragments.addItem.view

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
import com.gharieb.yomicepatask.core.common.dialogs.confirmCancelDialog
import com.gharieb.yomicepatask.core.common.dialogs.okDialog
import com.gharieb.yomicepatask.core.common.listeners.ClickListener
import com.gharieb.yomicepatask.core.common.listeners.EditClickListener
import com.gharieb.yomicepatask.core.handles.HandleStates
import com.gharieb.yomicepatask.core.handles.TaskResult
import com.gharieb.yomicepatask.databinding.FragmentItemsBinding
import com.gharieb.yomicepatask.domain.entity.main.items.ItemsResponseItem
import com.gharieb.yomicepatask.domain.entity.main.items.SuccessResponse
import com.gharieb.yomicepatask.presentation.fragments.addItem.adapter.ItemsAdapter
import com.gharieb.yomicepatask.presentation.fragments.addItem.viewModel.ItemsViewModel
import com.gharieb.yomicepatask.presentation.sharedViewModel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ItemsFragment : Fragment(), ClickListener, EditClickListener {
    private var _binding: FragmentItemsBinding? = null
    private val binding get() = _binding!!
    private lateinit var itemsAdapter: ItemsAdapter
    private val viewModel: ItemsViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentItemsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        observerGetItems()
        observerDeleteItem()
    }

    private fun observerGetItems() {
        viewModel.itemsToReturnRequest(
            pharmacyId =  sharedViewModel.getPharmacyId().value!!,
            returnRequestId = sharedViewModel.getReturnRequestId().value!!
        )
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.itemsRequestState.collect{ result ->
                result?.let {
                    when (result) {
                        is TaskResult.Success -> handleItemsSuccessResult(result.value)
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

    private fun observerDeleteItem() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.deleteItemRequestState.collect{ result ->
                result?.let {
                    when (result) {
                        is TaskResult.Success -> handleDeleteItemSuccessResult(result.value)
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

    private fun handleDeleteItemSuccessResult(successResponse: SuccessResponse) {
        okDialog(message = successResponse.message,context = requireContext())
        viewModel.itemsToReturnRequest(
            pharmacyId =  sharedViewModel.getPharmacyId().value!!,
            returnRequestId = sharedViewModel.getReturnRequestId().value!!
        )
        okDialog(message = successResponse.message,context = requireContext())
    }

    private fun handleItemsSuccessResult(items: List<ItemsResponseItem>) {
        alertDialogProgressBar?.dismiss()
        itemsAdapter.differ.submitList(items)
    }

    private fun setupAdapter(){
        itemsAdapter = ItemsAdapter(this,this)
        binding.itemsRecyclerView.adapter = itemsAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun onDeleteIconClick(itemId: Int) {
        confirmCancelDialog(getString(R.string.are_you_sure_you_want_to_delete_this_item),requireContext()){
            viewModel.deleteItemToReturnRequest(
                pharmacyId =  sharedViewModel.getPharmacyId().value!!,
                returnRequestId = sharedViewModel.getReturnRequestId().value!!,
                itemId = itemId
            )
        }
    }

    override fun onItemClick(id: Int) {
        onDeleteIconClick(itemId = id)
    }

    override fun <T> onItemClick(data: T) {
        onEditIconClick(item = data as ItemsResponseItem)
    }

    private fun onEditIconClick(item: ItemsResponseItem) {
        sharedViewModel.setItemReturnRequest(item)
        findNavController().navigate(R.id.action_itemsFragment_to_editItemBottomSheet)
    }
}