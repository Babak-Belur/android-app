package com.babakbelur.studiary.presentation.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.babakbelur.studiary.R
import com.babakbelur.studiary.core.data.ResultState
import com.babakbelur.studiary.core.utils.onFailure
import com.babakbelur.studiary.core.utils.onSuccess
import com.babakbelur.studiary.databinding.FragmentHomeBinding
import com.babakbelur.studiary.presentation.adapter.TargetAdapter
import com.babakbelur.studiary.presentation.base.BaseFragment
import com.babakbelur.studiary.presentation.ui.addtarget.AddTargetFragment.Companion.ARG_USER_ID
import com.babakbelur.studiary.presentation.ui.detail.DetailFragment.Companion.ARG_TARGET_ID
import com.babakbelur.studiary.presentation.ui.home.MainActivity.Companion.EXTRA_NAME
import com.babakbelur.studiary.presentation.ui.home.MainActivity.Companion.EXTRA_USER_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val targetAdapter: TargetAdapter by lazy { TargetAdapter() }

    private val viewModel: HomeViewModel by viewModels()

    private var userId: Int? = 0
    private var name: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRv()

        getIdUserFromIntent()

        addNewTarget()

        observeAllTarget()

        navigateToDetailTarget()
    }

    private fun initRv() = binding.rvTarget.apply {
        adapter = targetAdapter
        layoutManager = LinearLayoutManager(requireActivity())
    }

    @SuppressLint("SetTextI18n")
    private fun getIdUserFromIntent() {
        userId = requireActivity().intent.extras?.getInt(EXTRA_USER_ID)
        name = requireActivity().intent.extras?.getString(EXTRA_NAME)
        binding.tvHiName.text = "Hi, $name!"
    }

    private fun observeAllTarget() {
        viewModel.getAllTargetsUser(userId!!)
        viewModel.listTarget.observe(viewLifecycleOwner) { result ->
            val isLoading = result is ResultState.Loading
            val isSuccess = result is ResultState.Success

            binding.pbHome.isVisible = isLoading

            binding.rvTarget.isVisible = isSuccess
            result.onSuccess { resultData ->
                val isListTargetNullOrEmpty = resultData.data.target.isNullOrEmpty()
                if (isListTargetNullOrEmpty) {
                    binding.lottieEmpty.isVisible = true
                } else {
                    binding.lottieEmpty.isVisible = false
                    val listTarget = resultData.data.target
                    targetAdapter.submitList(listTarget)
                }
            }

            result.onFailure { throwable ->
                Toast.makeText(requireActivity(), throwable.message, Toast.LENGTH_SHORT).show()
                Log.e(HomeFragment::class.simpleName, throwable.message.toString())
            }
        }
    }

    private fun navigateToDetailTarget() {
        targetAdapter.onItemClick = { targetItem ->
            Bundle().run {
                putInt(ARG_TARGET_ID, targetItem.idTarget)
                findNavController().navigate(R.id.action_homeFragment_to_detailFragment, this)
            }
        }
    }

    private fun addNewTarget() {
        binding.fabAdd.setOnClickListener {
            Bundle().run {
                putInt(ARG_USER_ID, userId!!)
                findNavController().navigate(R.id.action_homeFragment_to_addTargetFragment, this)
            }
        }
    }

}