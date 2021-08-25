package com.babakbelur.studiary.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.babakbelur.studiary.R
import com.babakbelur.studiary.databinding.FragmentSignInBinding
import com.babakbelur.studiary.presentation.base.BaseFragment
import com.babakbelur.studiary.presentation.ui.home.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

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
            parentFragmentManager.beginTransaction().apply {
                replace(
                    R.id.login_container,
                    SignUpFragment.newInstance(),
                    SignUpFragment::class.java.simpleName
                )
                addToBackStack(SignInFragment::class.java.simpleName)
                commit()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SignInFragment()
    }

}