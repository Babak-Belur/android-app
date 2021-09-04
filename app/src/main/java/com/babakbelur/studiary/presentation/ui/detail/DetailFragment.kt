package com.babakbelur.studiary.presentation.ui.detail

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
import com.babakbelur.studiary.databinding.FragmentDetailBinding
import com.babakbelur.studiary.presentation.adapter.EvalAdapter
import com.babakbelur.studiary.presentation.base.BaseFragment
import com.babakbelur.studiary.presentation.utils.Constants.ARG_TARGET_ID
import com.babakbelur.studiary.presentation.utils.Constants.ARG_USER_ID
import com.babakbelur.studiary.presentation.utils.toLetterDateFormat
import com.babakbelur.studiary.presentation.utils.toNumberDateFormat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel: DetailViewModel by viewModels()

    private val navArgs: DetailFragmentArgs by navArgs()

    private val evalAdapter: EvalAdapter by lazy { EvalAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRv()

        observeDetailTarget()

    }

    private fun observeDetailTarget() {
        viewModel.getDetailTarget(navArgs.targetId)
        viewModel.detailTarget.observe(viewLifecycleOwner) { result ->
            val isLoading = result is ResultState.Loading

            binding.pbDetailTarget.isVisible = isLoading

            result.onSuccess { resultData ->
                val target = resultData.data[0]
                binding.apply {
                    tvSubject.text = target.course[0].courseName
                    tvDescription.text = target.course[0].description
                    tvDate.text = target.targetTime?.toNumberDateFormat()?.toLetterDateFormat()
                    tvTargetScore.text = target.gradeTarget.toString()
                }
                getAllUserEvaluations(navArgs.userId)
                navigateToEvaluation(navArgs.userId, navArgs.targetId)
            }

            result.onFailure { throwable ->
                Toast.makeText(requireActivity(), getString(R.string.error), Toast.LENGTH_SHORT)
                    .show()
                Log.e(DetailFragment::class.simpleName, throwable.message.toString())
            }
        }
    }

    private fun getAllUserEvaluations(userId: Int) {
        viewModel.getALlUserEvaluations(userId)
        viewModel.listUserEvaluations.observe(viewLifecycleOwner) { result ->

            result.onSuccess { resultData ->
                val eval = resultData.data

                if (eval.evaluation.isNotEmpty()) {
                    binding.lottieQuiz.isVisible = false
                    evalAdapter.submitList(eval.evaluation)
                    val evaluationId = eval.evaluation[eval.evaluation.lastIndex].idEvaluation
                    observePredictedScore(evaluationId)
                } else {
                    binding.lottieQuiz.isVisible = true
                }

            }
        }
    }

    private fun observePredictedScore(evaluationId: Int) {
        viewModel.getPredictedScore(evaluationId)
        viewModel.predictedScore.observe(viewLifecycleOwner) { result ->

            result.onSuccess { resultData ->
                val predict = resultData.data[0]
                val predictedScore = predict.predictedScore * 100
                binding.tvPredictedScore.text = predictedScore.toInt().toString()
            }
        }
    }

    private fun navigateToEvaluation(userId: Int, targetId: Int) {
        binding.btnEval.setOnClickListener {
            Bundle().run {
                putInt(ARG_USER_ID, userId)
                putInt(ARG_TARGET_ID, targetId)
                findNavController().navigate(R.id.action_detailFragment_to_evaluationFragment, this)
            }
        }
    }

    private fun initRv() = binding.rvEval.apply {
        adapter = evalAdapter
        layoutManager = LinearLayoutManager(requireActivity())
        setHasFixedSize(true)
    }
}