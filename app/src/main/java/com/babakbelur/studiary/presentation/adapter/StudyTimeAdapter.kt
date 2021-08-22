package com.babakbelur.studiary.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.babakbelur.studiary.core.domain.model.RangeTime
import com.babakbelur.studiary.databinding.ItemHorizontalButtonBinding

open class StudyTimeAdapter : BaseAdapter<RangeTime, ItemHorizontalButtonBinding>(
    ItemHorizontalButtonBinding::inflate,
    diffCallback
) {
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val rangeTime = getItem(position)
        val binding = ItemHorizontalButtonBinding.bind(holder.itemView)
        with(binding) {
            tvStudyTime.text = rangeTime.rangeTime
            root.setOnClickListener {
                onItemClick?.invoke(rangeTime)
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<RangeTime>() {
            override fun areItemsTheSame(oldItem: RangeTime, newItem: RangeTime): Boolean {
                return oldItem.rangeTime == newItem.rangeTime
            }

            override fun areContentsTheSame(oldItem: RangeTime, newItem: RangeTime): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class FreeTimeAdapter: StudyTimeAdapter()