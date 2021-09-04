package com.babakbelur.studiary.presentation.ui.addtarget

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.babakbelur.studiary.R
import com.babakbelur.studiary.core.data.ResultState
import com.babakbelur.studiary.core.utils.onFailure
import com.babakbelur.studiary.core.utils.onSuccess
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

    private val args: PreTestFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        fetchQuestion()
        observeScore()
        submitTarget()
    }

    private fun observeScore() {
        viewModel.calculatePreTestScore(testAdapter)
        viewModel.preTestScore.observe(viewLifecycleOwner) {
            score = it
        }
    }

    private fun submitTarget() {
        binding.btnDone.setOnClickListener {
            viewModel.addTarget(
                args.userId,
                args.courseId,
                score,
                args.targetScore,
                args.targetTime
            )
            viewModel.addTarget.observe(viewLifecycleOwner) { result ->
                val isLoading = result is ResultState.Loading

                binding.pbSubmitTarget.isVisible = isLoading

                result.onSuccess {
                    findNavController().navigate(R.id.action_preTestFragment_to_homeFragment)
                }

                result.onFailure {
                    Toast.makeText(
                        requireActivity(),
                        getString(R.string.error_add_target),
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e(PreTestFragment::class.simpleName, it.message.toString())
                }

            }
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