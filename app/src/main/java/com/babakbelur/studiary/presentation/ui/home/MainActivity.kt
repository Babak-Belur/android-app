package com.babakbelur.studiary.presentation.ui.home

import android.os.Bundle
import com.babakbelur.studiary.databinding.ActivityMainBinding
import com.babakbelur.studiary.presentation.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}