package com.babakbelur.studiary.presentation.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.babakbelur.studiary.databinding.FragmentEvaluationBinding
import com.babakbelur.studiary.presentation.adapter.FreeTimeAdapter
import com.babakbelur.studiary.presentation.adapter.StudyTimeAdapter
import com.babakbelur.studiary.presentation.adapter.TestQuestionAdapter
import com.babakbelur.studiary.presentation.base.BaseFragment
import com.babakbelur.studiary.presentation.utils.Constants.FREE_TIME_CATEGORIES
import com.babakbelur.studiary.presentation.utils.Constants.LIST_QUESTIONS
import com.babakbelur.studiary.presentation.utils.Constants.STUDY_TIME_CATEGORIES

class EvaluationFragment :
    BaseFragment<FragmentEvaluationBinding>(FragmentEvaluationBinding::inflate) {

    private val evaluationAdapter: TestQuestionAdapter by lazy { TestQuestionAdapter() }

    private val viewModel: DetailViewModel by viewModels()

    private val studyTimeAdapter: StudyTimeAdapter by lazy { StudyTimeAdapter() }

    private val freeTimeAdapter: FreeTimeAdapter by lazy { FreeTimeAdapter() }

    private var score: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRv()

        fetchValueAdapter()

        observeEvaluationScore()

        handleItemButtonClicked()
    }

    private fun observeEvaluationScore() {
        viewModel.calculatePreTestScore(evaluationAdapter)
        viewModel.evalScore.observe(viewLifecycleOwner) {
            score = it
        }
    }

    private fun handleItemButtonClicked() {
        studyTimeAdapter.onItemClick = { rangeTime ->

        }

        freeTimeAdapter.onItemClick = { rangeTime ->

        }
    }

    private fun initRv() {
        binding.rvQuestion.apply {
            adapter = evaluationAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
        binding.rvStudyTime.apply {
            adapter = studyTimeAdapter
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        }
        binding.rvFreeTime.apply {
            adapter = freeTimeAdapter
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun fetchValueAdapter() {
        evaluationAdapter.submitList(LIST_QUESTIONS)
        studyTimeAdapter.submitList(STUDY_TIME_CATEGORIES)
        freeTimeAdapter.submitList(FREE_TIME_CATEGORIES)
    }
}