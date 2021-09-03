package com.babakbelur.studiary.presentation.adapter

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.babakbelur.studiary.core.domain.model.TargetItem
import com.babakbelur.studiary.databinding.ItemTargetBinding
import com.babakbelur.studiary.presentation.utils.ddMMMMyyyyFormat
import com.babakbelur.studiary.presentation.utils.toNumberDateFormat
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

class TargetAdapter(context: Context) : BaseAdapter<TargetItem, ItemTargetBinding>(
    ItemTargetBinding::inflate,
    diffCallback
) {

    private var calendar: Calendar

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface MyEntryPoint {
        fun getCalendarInstance(): Calendar
    }

    init {
        val myEntryPoint = EntryPointAccessors.fromApplication(context, MyEntryPoint::class.java)
        calendar = myEntryPoint.getCalendarInstance()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val target = getItem(position)
        val binding = ItemTargetBinding.bind(holder.itemView)
        val currentDay = calendar.time.ddMMMMyyyyFormat()
        with(binding) {
            val totalDay = calculateTotalDays(currentDay, target.targetTime!!.toNumberDateFormat().ddMMMMyyyyFormat())
            tvTotalDays.text = totalDay.toString()
            tvSubjects.text = target.course[0].courseName
            tvDescription.text = target.course[0].description

            root.setOnClickListener {
                onItemClick?.invoke(target)
            }
        }
    }

    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    private fun calculateTotalDays(currentDay: String, selectedDay: String): Int {
        val sdf = SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault())
        val currentDate = sdf.parse(currentDay)
        val selectedDate = sdf.parse(selectedDay)
        val difference = abs(currentDate.time - selectedDate.time)
        val diffDays = difference / (24 * 60 * 60 * 1000)
        val totalDays = diffDays.toInt()
        Log.i("Test", "Total Days: $totalDays")
        return totalDays
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