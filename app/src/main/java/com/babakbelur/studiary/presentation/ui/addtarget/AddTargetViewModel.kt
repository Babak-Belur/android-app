package com.babakbelur.studiary.presentation.ui.addtarget

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

class AddTargetViewModel : ViewModel() {

    val selectedDate: MutableLiveData<String> = MutableLiveData()

    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun calculateTotalDays(currentDay: String, selectedDay: String): Int {
        val sdf = SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault())
        val currentDate = sdf.parse(currentDay)
        val selectedDate = sdf.parse(selectedDay)
        val difference = abs(currentDate.time - selectedDate.time)
        val diffDays = difference / (24 * 60 * 60 * 1000)
        val totalDays = diffDays.toInt()
        Log.i("Test", "Total Days: $totalDays")
        return totalDays
    }
}