package com.babakbelur.studiary.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.babakbelur.studiary.R
import com.babakbelur.studiary.core.data.ResultState
import com.babakbelur.studiary.core.utils.onFailure
import com.babakbelur.studiary.core.utils.onSuccess
import com.babakbelur.studiary.databinding.FragmentSignUpBinding
import com.babakbelur.studiary.presentation.base.BaseFragment
import com.babakbelur.studiary.presentation.ui.home.MainActivity
import com.babakbelur.studiary.presentation.ui.home.MainActivity.Companion.EXTRA_USER_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigateToSignInFragment()

        observeSignUpRequest()
    }

    private fun observeSignUpRequest() {
        binding.btnSignUp.setOnClickListener {
            val name = binding.etName.text.toString()
            var username = binding.etUsernameSignUp.text.toString()
            var password = binding.etPassword.text.toString()

            viewModel.signUpRequest(name, username, password)
            viewModel.signUp.observe(viewLifecycleOwner) { result ->
                val isLoading = result is ResultState.Loading

                binding.progressBarSignUp.isVisible = isLoading

                result.onSuccess { resultData ->
                    Toast.makeText(
                        requireActivity(),
                        getString(R.string.success_sign_up),
                        Toast.LENGTH_SHORT
                    ).show()
                    username = resultData.data[0].username!!
                    password = resultData.data[0].password!!
                    observeSignInRequest(username, password)
                }

                result.onFailure { throwable ->
                    Toast.makeText(
                        requireActivity(),
                        getString(R.string.error_sign_up),
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.e(SignUpFragment::class.simpleName, throwable.message.toString())
                }
            }
        }
    }

    private fun observeSignInRequest(username: String, password: String) {
        viewModel.signInRequest(username, password)
        viewModel.signIn.observe(viewLifecycleOwner) { result ->

            result.onSuccess { resultData ->
                val userId = resultData.data.user[0].idUser
                val name = resultData.data.user[0].name!!
                navigateToMainActivity(userId, name)
            }

            result.onFailure { throwable ->
                Log.e(SignUpFragment::class.simpleName, throwable.message.toString())
            }
        }
    }

    private fun navigateToMainActivity(userId: Int, name: String) {
        Intent(requireActivity(), MainActivity::class.java).run {
            putExtra(EXTRA_USER_ID, userId)
            putExtra(MainActivity.EXTRA_NAME, name)
            startActivity(this)
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