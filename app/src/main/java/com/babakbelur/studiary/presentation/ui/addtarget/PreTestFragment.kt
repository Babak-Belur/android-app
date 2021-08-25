package com.babakbelur.studiary.presentation.ui.addtarget

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.babakbelur.studiary.R
import com.babakbelur.studiary.databinding.FragmentPreTestBinding
import com.babakbelur.studiary.presentation.adapter.TestQuestionAdapter
import com.babakbelur.studiary.presentation.base.BaseFragment
import com.babakbelur.studiary.presentation.utils.Constants.LIST_QUESTIONS
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreTestFragment : BaseFragment<FragmentPreTestBinding>(FragmentPreTestBinding::inflate) {

    private val testAdapter: TestQuestionAdapter by lazy { TestQuestionAdapter() }

    private val viewModel: AddTargetViewModel by viewModels()

    private var score = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        fetchQuestion()
        observeScore()
        navigateToHome()
    }

    private fun observeScore() {
        viewModel.calculatePreTestScore(testAdapter)
        viewModel.preTestScore.observe(viewLifecycleOwner) {
            score = it
        }
    }

    private fun navigateToHome() {
        binding.btnDone.setOnClickListener {
            findNavController().navigate(R.id.action_preTestFragment_to_homeFragment)
        }
    }

    private fun initRv() = binding.rvQuestion.apply {
        adapter = testAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun fetchQuestion() {
        testAdapter.submitList(LIST_QUESTIONS)
    }
}