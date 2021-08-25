package com.babakbelur.studiary.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.babakbelur.studiary.core.domain.model.UserItem
import com.babakbelur.studiary.core.domain.usecase.IAppUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val useCase: IAppUseCase) : ViewModel() {

    fun signIn(userItem: UserItem) = useCase.signIn(userItem).asLiveData()

    fun signUp(userItem: UserItem) = useCase.signUp(userItem).asLiveData()
}