package com.babakbelur.studiary.presentation.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.babakbelur.studiary.R
import com.babakbelur.studiary.core.data.ResultState
import com.babakbelur.studiary.core.utils.onFailure
import com.babakbelur.studiary.core.utils.onSuccess
import com.babakbelur.studiary.databinding.FragmentDetailBinding
import com.babakbelur.studiary.presentation.base.BaseFragment
import com.babakbelur.studiary.presentation.utils.toLetterDateFormat
import com.babakbelur.studiary.presentation.utils.toNumberDateFormat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel: DetailViewModel by viewModels()

    private val navArgs: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            }

            result.onFailure { throwable ->
                Toast.makeText(requireActivity(), getString(R.string.error), Toast.LENGTH_SHORT).show()
                Log.e(DetailFragment::class.simpleName, throwable.message.toString())
            }
        }
    }

    companion object {
        const val ARG_TARGET_ID = "targetId"
    }
}