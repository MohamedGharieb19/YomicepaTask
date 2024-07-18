package com.gharieb.yomicepatask.presentation.fragments.pharmacies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gharieb.yomicepatask.R
import com.gharieb.yomicepatask.core.common.listeners.ClickListener
import com.gharieb.yomicepatask.databinding.PharmaciesItemBinding
import com.gharieb.yomicepatask.domain.entity.main.pharmacies.PharmaciesItem

class PharmaciesAdapter(
    private val onClickListener: ClickListener? = null,
) : RecyclerView.Adapter<PharmaciesAdapter.ViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<PharmaciesItem>() {
        override fun areItemsTheSame(
            oldItem: PharmaciesItem,
            newItem: PharmaciesItem
        ): Boolean {
            return oldItem.pharmacyId == newItem.pharmacyId
        }

        override fun areContentsTheSame(
            oldItem: PharmaciesItem,
            newItem: PharmaciesItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtil)

    class ViewHolder(val binding: PharmaciesItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            PharmaciesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.binding.apply {
            titleText.text = data.doingBusinessAs
            descriptionText.text = holder.itemView.context.getString(R.string.no_of_returns).plus(data.numberOfReturns)
        }
        holder.itemView.setOnClickListener {
            onClickListener?.onItemClick(id = data.pharmacyId!!)
        }
    }
}