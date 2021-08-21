package com.babakbelur.studiary.presentation.ui.addtarget

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.babakbelur.studiary.R
import com.babakbelur.studiary.databinding.FragmentAddTargetBinding
import com.babakbelur.studiary.presentation.base.BaseFragment
import com.babakbelur.studiary.presentation.utils.ddMMMMyFormat
import com.babakbelur.studiary.presentation.utils.ddMMMMyyyyFormat
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.math.abs

@AndroidEntryPoint
class AddTargetFragment : BaseFragment<FragmentAddTargetBinding>(FragmentAddTargetBinding::inflate),
    DatePickerDialog.OnDateSetListener {

    private val viewModel: AddTargetViewModel by viewModels()

    private var totalDays: Int = 0
    private var targetScore: Int = 0
    private var currentDay: String = ""
    private var selectedDay: String = ""

    @Inject
    lateinit var calendar: Calendar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addNewTarget()

        selectDate()

        setInitialDate()

        selectTargetScore()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val stringBuilder = StringBuilder().run {
            append(year)
            append("-")
            append(month + 1)
            append("-")
            append(dayOfMonth)
        }
        selectedDay = stringBuilder.toString().ddMMMMyyyyFormat()
        binding.tvDate.text = stringBuilder.toString().ddMMMMyFormat()
        calculateTotalDays()
    }

    private fun setInitialDate() {
        if (selectedDay.isEmpty()) {
            binding.tvDate.text = calendar.time.ddMMMMyFormat()
        } else {
            binding.tvDate.text = selectedDay
        }
        currentDay = calendar.time.ddMMMMyyyyFormat()
    }

    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    private fun calculateTotalDays() {
        val sdf = SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault())
        val currentDate = sdf.parse(currentDay)
        val selectedDate = sdf.parse(selectedDay)
        val difference = abs(currentDate.time - selectedDate.time)
        val diffDays = difference / (24 * 60 * 60 * 1000)
        totalDays = diffDays.toInt()
        Log.i("Test", "Total Days: $totalDays")
    }

    private fun selectTargetScore() {
        val score = binding.etTargetScore.text.toString()
        targetScore = score.toInt()
        Log.i("Test", "Target Score: $targetScore")
    }

    private fun selectDate() {
        binding.cardDate.setOnClickListener {
            val datePickerInstance = DatePickerDialogFragment.getInstance()
            datePickerInstance.setListeningActivity(this)
            datePickerInstance.show(childFragmentManager, DATE_PICKER_TAG)
        }
    }

    private fun addNewTarget() {
        binding.btnSubmit.setOnClickListener {
            findNavController().navigate(R.id.action_addTargetFragment_to_preTestFragment)
        }
    }

    companion object {
        private const val DATE_PICKER_TAG = "DatePicker"
    }

}