package com.babakbelur.studiary.presentation.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.babakbelur.studiary.R
import com.babakbelur.studiary.core.domain.model.RangeTime
import com.babakbelur.studiary.databinding.ItemHorizontalButtonBinding
import com.google.android.material.card.MaterialCardView

open class StudyTimeAdapter : BaseAdapter<RangeTime, ItemHorizontalButtonBinding>(
    ItemHorizontalButtonBinding::inflate,
    diffCallback
) {
    private var indexButtonSelected = RecyclerView.NO_POSITION

    var onItemChecked: ((RangeTime, Boolean, MaterialCardView) -> Unit)? = null

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val rangeTime = getItem(position)
        val binding = ItemHorizontalButtonBinding.bind(holder.itemView)
        with(binding) {
            root.isSelected = indexButtonSelected == position
            tvStudyTime.text = rangeTime.rangeTime
            root.setOnClickListener {
                indexButtonSelected = holder.layoutPosition
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