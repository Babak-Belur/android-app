package com.babakbelur.studiary.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.babakbelur.studiary.R
import com.babakbelur.studiary.databinding.FragmentHomeBinding
import com.babakbelur.studiary.presentation.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addNewTarget()
    }

    private fun addNewTarget() {
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addTargetFragment)
        }
    }

}