package com.babakbelur.studiary.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.babakbelur.studiary.R
import com.babakbelur.studiary.core.data.ResultState
import com.babakbelur.studiary.core.utils.onFailure
import com.babakbelur.studiary.core.utils.onSuccess
import com.babakbelur.studiary.databinding.FragmentSignInBinding
import com.babakbelur.studiary.presentation.base.BaseFragment
import com.babakbelur.studiary.presentation.ui.home.MainActivity
import com.babakbelur.studiary.presentation.ui.home.MainActivity.Companion.EXTRA_NAME
import com.babakbelur.studiary.presentation.ui.home.MainActivity.Companion.EXTRA_USER_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigateToSignUpFragment()

        observeSignInRequest()

    }

    private fun observeSignInRequest() {
        binding.btnSignIn.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.signInRequest(username, password)
            viewModel.signIn.observe(viewLifecycleOwner) { result ->
                val isLoading = result is ResultState.Loading

                result.onSuccess { resultData ->
                    val userId = resultData.data.user[0].idUser
                    val name = resultData.data.user[0].name!!
                    Toast.makeText(
                        requireActivity(),
                        getString(R.string.success_login),
                        Toast.LENGTH_SHORT
                    ).show()
                    navigateToMainActivity(userId, name)
                }

                result.onFailure { throwable ->
                    Toast.makeText(requireActivity(), throwable.message, Toast.LENGTH_SHORT).show()
                    binding.etPassword.text.clear()
                    binding.etPassword.requestFocus()
                }

                binding.progressBar.isVisible = isLoading
            }
        }
    }

    private fun navigateToMainActivity(userId: Int, name: String) {
        Intent(requireActivity(), MainActivity::class.java).run {
            putExtra(EXTRA_USER_ID, userId)
            putExtra(EXTRA_NAME, name)
            startActivity(this)
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