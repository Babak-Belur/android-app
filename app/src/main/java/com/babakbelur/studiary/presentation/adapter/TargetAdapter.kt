package com.babakbelur.studiary.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.babakbelur.studiary.core.domain.model.TargetItem
import com.babakbelur.studiary.databinding.ItemTargetBinding

class TargetAdapter : BaseAdapter<TargetItem, ItemTargetBinding>(
    ItemTargetBinding::inflate,
    diffCallback
) {
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val target = getItem(position)
        val binding = ItemTargetBinding.bind(holder.itemView)
        with(binding) {
            tvTotalDays.text = target.targetTime.toString()
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<TargetItem>() {
            override fun areItemsTheSame(oldItem: TargetItem, newItem: TargetItem): Boolean {
                return oldItem.idTarget == newItem.idTarget
            }

            override fun areContentsTheSame(oldItem: TargetItem, newItem: TargetItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}