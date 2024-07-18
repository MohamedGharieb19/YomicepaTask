package com.gharieb.yomicepatask.presentation.fragments.pharmacyDetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gharieb.yomicepatask.R
import com.gharieb.yomicepatask.databinding.ReturnRequestsItemBinding
import com.gharieb.yomicepatask.domain.entity.main.returnRequests.ContentItem

class ReturnRequestsAdapter(
    private val wholesalers: String? = ""
) : RecyclerView.Adapter<ReturnRequestsAdapter.ViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<ContentItem>() {
        override fun areItemsTheSame(
            oldItem: ContentItem,
            newItem: ContentItem
        ): Boolean {
            return oldItem.returnRequest?.id == newItem.returnRequest?.id
        }

        override fun areContentsTheSame(
            oldItem: ContentItem,
            newItem: ContentItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtil)

    class ViewHolder(val binding: ReturnRequestsItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ReturnRequestsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position].returnRequest
        holder.binding.apply {
            idText.text = data?.id.toString()
            createdAtText.text = data?.createdAt.toString()
            serviceTypeText.text = data?.serviceType
            statusText.text = data?.returnRequestStatus
            wholesalerText.text = holder.itemView.context.getText(R.string.wholesaler).toString().plus(wholesalers)
        }
    }
}