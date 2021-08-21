package com.babakbelur.studiary.presentation.ui.addtarget

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.babakbelur.studiary.databinding.FragmentPreTestBinding
import com.babakbelur.studiary.presentation.adapter.TestQuestionAdapter
import com.babakbelur.studiary.presentation.base.BaseFragment
import com.babakbelur.studiary.presentation.utils.Constants.LIST_QUESTIONS

class PreTestFragment : BaseFragment<FragmentPreTestBinding>(FragmentPreTestBinding::inflate) {

    private val testAdapter: TestQuestionAdapter by lazy { TestQuestionAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        fetchQuestion()
    }

    private fun initRv() = binding.rvQuestion.apply {
        adapter = testAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun fetchQuestion() {
        testAdapter.submitList(LIST_QUESTIONS)
    }
}