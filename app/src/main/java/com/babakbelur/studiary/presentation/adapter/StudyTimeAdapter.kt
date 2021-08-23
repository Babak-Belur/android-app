package com.babakbelur.studiary.presentation.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import com.babakbelur.studiary.R
import com.babakbelur.studiary.core.domain.model.RangeTime
import com.babakbelur.studiary.databinding.ItemHorizontalButtonBinding

open class StudyTimeAdapter : BaseAdapter<RangeTime, ItemHorizontalButtonBinding>(
    ItemHorizontalButtonBinding::inflate,
    diffCallback
) {
    private var indexButtonSelected = 0

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        indexButtonSelected = holder.adapterPosition
        val rangeTime = getItem(position)
        val binding = ItemHorizontalButtonBinding.bind(holder.itemView)
        with(binding) {

            tvStudyTime.text = rangeTime.rangeTime
            root.setOnClickListener {
                if (indexButtonSelected == position) {
                    btnStudyTime.setCardBackgroundColor(
                        ContextCompat.getColor(
                            holder.itemView.context,
                            R.color.purple_500
                        )
                    )
                } else {
                    btnStudyTime.setCardBackgroundColor(
                        ContextCompat.getColor(
                            holder.itemView.context,
                            android.R.color.transparent
                        )
                    )
                }
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

class FreeTimeAdapter : StudyTimeAdapter()