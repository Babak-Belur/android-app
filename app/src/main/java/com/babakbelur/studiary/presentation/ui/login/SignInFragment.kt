package com.babakbelur.studiary.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.babakbelur.studiary.R
import com.babakbelur.studiary.databinding.FragmentSignInBinding
import com.babakbelur.studiary.presentation.base.BaseFragment
import com.babakbelur.studiary.presentation.ui.home.MainActivity

class SignInFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigateToMainActivity()

        navigateToSignUpFragment()

    }

    private fun navigateToMainActivity() {
        binding.btnSignIn.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun navigateToSignUpFragment() {
        binding.tvCreateAccount.setOnClickListener {
            childFragmentManager.beginTransaction().apply {
                replace(
                    R.id.login_container,
                    SignUpFragment.newInstance(),
                    SignUpFragment::class.java.simpleName
                )
                commit()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SignInFragment()
    }

}