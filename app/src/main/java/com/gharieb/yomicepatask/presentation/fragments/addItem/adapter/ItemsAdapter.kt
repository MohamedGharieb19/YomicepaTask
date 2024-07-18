package com.gharieb.yomicepatask.presentation.fragments.addItem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gharieb.yomicepatask.core.common.listeners.ClickListener
import com.gharieb.yomicepatask.core.common.listeners.EditClickListener
import com.gharieb.yomicepatask.databinding.ItemsOfReturnRequestsItemBinding
import com.gharieb.yomicepatask.domain.entity.main.items.ItemsResponseItem

class ItemsAdapter(
    private val onClickListener: ClickListener? = null,
    private val onEditClickListener: EditClickListener? = null,
) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<ItemsResponseItem>() {
        override fun areItemsTheSame(
            oldItem: ItemsResponseItem,
            newItem: ItemsResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ItemsResponseItem,
            newItem: ItemsResponseItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtil)

    class ViewHolder(val binding: ItemsOfReturnRequestsItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemsOfReturnRequestsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.binding.apply {
            nameText.text = data.name
            createdAtText.text = data.createdAt
            descriptionText.text = data.description
            idText.text = data.id.toString()
            manufacturerText.text = data.manufacturer
            statusText.text = data.status
            deleteBtn.setOnClickListener {
                onClickListener?.onItemClick(id = data.id!!)
            }
            editBtn.setOnClickListener {
                onEditClickListener?.onItemClick(data as ItemsResponseItem)
            }
        }
    }
}