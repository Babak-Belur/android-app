package com.babakbelur.studiary.presentation.ui.login

import android.os.Bundle
import com.babakbelur.studiary.R
import com.babakbelur.studiary.databinding.ActivityLoginBinding
import com.babakbelur.studiary.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override fun getViewBinding(): ActivityLoginBinding =
        ActivityLoginBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val signInFragment = SignInFragment.newInstance()
        val fragment = supportFragmentManager.findFragmentByTag(SignInFragment::class.java.simpleName)

        if (fragment !is SignInFragment) {
            supportFragmentManager.beginTransaction()
                .add(R.id.login_container, signInFragment, SignInFragment::class.java.simpleName)
                .commit()
        }
    }
}