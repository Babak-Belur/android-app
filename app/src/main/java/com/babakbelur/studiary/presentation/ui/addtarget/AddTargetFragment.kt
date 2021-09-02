package com.babakbelur.studiary.presentation.ui.addtarget

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.DatePicker
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.babakbelur.studiary.R
import com.babakbelur.studiary.core.utils.onFailure
import com.babakbelur.studiary.core.utils.onSuccess
import com.babakbelur.studiary.databinding.FragmentAddTargetBinding
import com.babakbelur.studiary.presentation.base.BaseFragment
import com.babakbelur.studiary.presentation.ui.addtarget.PreTestFragment.Companion.ARG_COURSE_ID
import com.babakbelur.studiary.presentation.ui.addtarget.PreTestFragment.Companion.ARG_TARGET_SCORE
import com.babakbelur.studiary.presentation.ui.addtarget.PreTestFragment.Companion.ARG_TARGET_TIME
import com.babakbelur.studiary.presentation.utils.ddMMMMyFormat
import com.babakbelur.studiary.presentation.utils.ddMMMMyyyyFormat
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@AndroidEntryPoint
class AddTargetFragment : BaseFragment<FragmentAddTargetBinding>(FragmentAddTargetBinding::inflate),
    DatePickerDialog.OnDateSetListener {

    private val viewModel: AddTargetViewModel by viewModels()

    private val args: AddTargetFragmentArgs by navArgs()

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

        observeAllCourses()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val stringBuilder = StringBuilder().run {
            append(year)
            append("-")
            append(month + 1)
            append("-")
            append(dayOfMonth)
        }
        selectedDay = stringBuilder.toString()
        binding.tvDate.text = stringBuilder.toString().ddMMMMyFormat()
    }

    private fun setInitialDate() {
        if (selectedDay.isEmpty()) {
            binding.tvDate.text = calendar.time.ddMMMMyFormat()
        } else {
            binding.tvDate.text = selectedDay.ddMMMMyFormat()
        }
        currentDay = calendar.time.ddMMMMyyyyFormat()
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

    private fun observeAllCourses() {
        viewModel.getAllCourses()
        viewModel.listCourses.observe(viewLifecycleOwner) { result ->

            result.onSuccess { resultData ->
                var idCourse: Int
                val listCourses = resultData.data
                val subjects = ArrayList<String>()
                listCourses.forEach {
                    idCourse = it.idCourse
                    subjects.add("${it.courseName} - $idCourse")
                }
                val adapter = ArrayAdapter(requireContext(), R.layout.item_subject_course, subjects)
                (binding.tfSubject.editText as? AutoCompleteTextView)?.setAdapter(adapter)
            }

            result.onFailure { throwable ->
                Log.e(AddTargetFragment::class.simpleName, throwable.message.toString())
            }
        }
    }

    private fun addNewTarget() {
        binding.btnSubmit.setOnClickListener {
            val subject = binding.tfSubject.editText?.text.toString()
            val isSubjectEmpty = subject.isEmpty()

            val targetScore = binding.etTargetScore.text.toString()
            val isScoreEmpty = targetScore.isEmpty() || targetScore == "0"

            if (!isSubjectEmpty && !isScoreEmpty) {
                val courseId = subject.replace("[^0-9]".toRegex(), "").toInt()
                Bundle().run {
                    putInt(ARG_USER_ID, args.userId)
                    putString(ARG_TARGET_TIME, selectedDay)
                    putInt(ARG_TARGET_SCORE, targetScore.toInt())
                    putInt(ARG_COURSE_ID, courseId)
                    findNavController().navigate(
                        R.id.action_addTargetFragment_to_preTestFragment,
                        this
                    )
                }
            } else {
                handleEmptyView(isSubjectEmpty, isScoreEmpty)
            }

        }
    }

    private fun handleEmptyView(isSubjectEmpty: Boolean, isScoreEmpty: Boolean) {
        when {
            isSubjectEmpty -> binding.tfSubject.error = getString(R.string.error_subject)
            isScoreEmpty -> binding.etTargetScore.error = getString(R.string.error_target_score)
            isSubjectEmpty && isScoreEmpty -> {
                binding.etTargetScore.error = getString(R.string.error_target_score)
                binding.tfSubject.error = getString(R.string.error_subject)
            }
            else -> {
                binding.etTargetScore.error = null
                binding.tfSubject.error = null
            }
        }
    }

    companion object {
        private const val DATE_PICKER_TAG = "DatePicker"
        const val ARG_USER_ID = "userId"
    }

}