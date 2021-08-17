package com.babakbelur.studiary.presentation.ui.login

import android.os.Bundle
import com.babakbelur.studiary.databinding.ActivityLoginBinding
import com.babakbelur.studiary.presentation.base.BaseActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override fun getViewBinding(): ActivityLoginBinding =
        ActivityLoginBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}