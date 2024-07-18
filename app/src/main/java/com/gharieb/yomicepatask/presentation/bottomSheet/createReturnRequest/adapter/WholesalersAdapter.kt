package com.gharieb.yomicepatask.presentation.bottomSheet.createReturnRequest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gharieb.yomicepatask.core.common.listeners.ClickListener
import com.gharieb.yomicepatask.databinding.WholesalersItemBinding
import com.gharieb.yomicepatask.domain.entity.main.wholesalers.WholesalersResponseItem

class WholesalersAdapter(
    private val onClickListener: ClickListener? = null,
) : RecyclerView.Adapter<WholesalersAdapter.ViewHolder>() {

    private var selectedRadioButton = -1

    private val diffUtil = object : DiffUtil.ItemCallback<WholesalersResponseItem>() {
        override fun areItemsTheSame(
            oldItem: WholesalersResponseItem,
            newItem: WholesalersResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: WholesalersResponseItem,
            newItem: WholesalersResponseItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtil)

    class ViewHolder(val binding: WholesalersItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            WholesalersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.binding.apply {
            wholesalerText.text = data.name
            radioItem.apply {
                isChecked = selectedRadioButton == position
                radioItem.setOnClickListener {
                    selectedRadioButton = holder.adapterPosition
                    onClickListener?.onItemClick(data.id!!)
                    notifyDataSetChanged()
                }
            }
        }
        holder.itemView.setOnClickListener {
            selectedRadioButton = holder.adapterPosition
            onClickListener?.onItemClick(data.id!!)
            notifyDataSetChanged()
        }
    }
}