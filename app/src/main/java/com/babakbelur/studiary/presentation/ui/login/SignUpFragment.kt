package com.babakbelur.studiary.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.babakbelur.studiary.R
import com.babakbelur.studiary.databinding.FragmentSignUpBinding
import com.babakbelur.studiary.presentation.base.BaseFragment
import com.babakbelur.studiary.presentation.ui.home.MainActivity

class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigateToMainActivity()

        navigateToSignInFragment()

    }

    private fun navigateToMainActivity() {
        binding.btnSignUp.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun navigateToSignInFragment() {
        binding.tvSignIn.setOnClickListener {
            childFragmentManager.beginTransaction().apply {
                replace(
                    R.id.login_container,
                    SignInFragment.newInstance(),
                    SignInFragment::class.java.simpleName
                )
                commit()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SignUpFragment()
    }
}