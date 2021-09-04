package com.babakbelur.studiary.presentation.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.babakbelur.studiary.R
import com.babakbelur.studiary.core.utils.onFailure
import com.babakbelur.studiary.core.utils.onSuccess
import com.babakbelur.studiary.databinding.FragmentEvaluationBinding
import com.babakbelur.studiary.presentation.adapter.TestQuestionAdapter
import com.babakbelur.studiary.presentation.base.BaseFragment
import com.babakbelur.studiary.presentation.utils.Constants.ARG_TARGET_ID
import com.babakbelur.studiary.presentation.utils.Constants.ARG_USER_ID
import com.babakbelur.studiary.presentation.utils.Constants.LIST_QUESTIONS
import com.babakbelur.studiary.presentation.utils.ddMMMMyFormat
import com.babakbelur.studiary.presentation.utils.yyyymmddFormat
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class EvaluationFragment :
    BaseFragment<FragmentEvaluationBinding>(FragmentEvaluationBinding::inflate) {

    private val evaluationAdapter: TestQuestionAdapter by lazy { TestQuestionAdapter() }

    private val viewModel: DetailViewModel by viewModels()

    private var score: Int = 0

    private var studyTimeIndicator: Int = 0

    private var freeTimeIndicator: Int = 0

    private val args: EvaluationFragmentArgs by navArgs()

    @Inject
    lateinit var calendar: Calendar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRv()

        observeEvaluationScore()

        handleFreeTimeChip()

        handleStudyTimeChip()

        observeEvaluation()
    }

    private fun observeEvaluation() {
        binding.btnSubmit.setOnClickListener {
            val userId = args.userId
            val targetId = args.targetId
            val currDate = calendar.time.ddMMMMyFormat().yyyymmddFormat()
            viewModel.addEvaluation(
                userId,
                currDate,
                score,
                studyTimeIndicator,
                freeTimeIndicator,
                targetId
            )
            viewModel.addEvaluation.observe(viewLifecycleOwner) { result ->

                result.onSuccess { _ ->
                    navigateToDetailTarget()
                }

                result.onFailure { throwable ->
                    Log.e(EvaluationFragment::class.simpleName, throwable.message.toString())
                    Toast.makeText(requireActivity(), "Something Wrong!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun observeEvaluationScore() {
        viewModel.calculatePreTestScore(evaluationAdapter)
        viewModel.evalScore.observe(viewLifecycleOwner) {
            score = it
        }
    }

    private fun handleFreeTimeChip() {
        binding.cgFreeTime.setOnCheckedChangeListener { _, checkedId ->
            binding.run {
                when (checkedId) {
                    chipFreeTime1.id -> freeTimeIndicator = 1
                    chipFreeTime2.id -> freeTimeIndicator = 2
                    chipFreeTime3.id -> freeTimeIndicator = 3
                    chipFreeTime4.id -> freeTimeIndicator = 4
                    chipFreeTime5.id -> freeTimeIndicator = 5
                }
            }
        }
    }

    private fun navigateToDetailTarget() {
        Bundle().run {
            putInt(ARG_USER_ID, args.userId)
            putInt(ARG_TARGET_ID, args.targetId)
            findNavController().navigate(R.id.action_evaluationFragment_to_detailFragment, this)
        }
    }

    private fun handleStudyTimeChip() {
        binding.cgStudyTime.setOnCheckedChangeListener { _, checkedId ->
            binding.run {
                when (checkedId) {
                    chipStudyTime1.id -> studyTimeIndicator = 1
                    chipStudyTime2.id -> studyTimeIndicator = 2
                    chipStudyTime3.id -> studyTimeIndicator = 3
                    chipStudyTime4.id -> studyTimeIndicator = 4
                }
            }
        }

    }

    private fun initRv() {
        binding.rvQuestion.apply {
            adapter = evaluationAdapter
            layoutManager = LinearLayoutManager(requireActivity())
            evaluationAdapter.submitList(LIST_QUESTIONS)
        }
    }
}