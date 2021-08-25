package com.babakbelur.studiary.presentation.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.babakbelur.studiary.R
import com.babakbelur.studiary.core.data.ResultState
import com.babakbelur.studiary.databinding.FragmentHomeBinding
import com.babakbelur.studiary.presentation.adapter.TargetAdapter
import com.babakbelur.studiary.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val targetAdapter: TargetAdapter by lazy { TargetAdapter() }

    private val viewModel: HomeViewModel by viewModels()

    private var idUser: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRv()

        addNewTarget()

        //observeAllTarget()

        navigateToDetailTarget()
    }

    private fun initRv() = binding.rvTarget.apply {
        adapter = targetAdapter
        layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun observeAllTarget() {
        viewModel.getTargetByIdUser(1).observe(viewLifecycleOwner) { resultState ->
            when (resultState) {
                is ResultState.Error -> Toast.makeText(requireActivity(), "Something Wrong", Toast.LENGTH_SHORT).show()
                is ResultState.Loading -> Unit
                is ResultState.Success -> {
                    targetAdapter.submitList(resultState.data)
                }
            }

        }
    }

    private fun navigateToDetailTarget() {
        targetAdapter.onItemClick = { targetItem ->
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
        }
    }

    private fun addNewTarget() {
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addTargetFragment)
        }
    }

}