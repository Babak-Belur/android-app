package com.babakbelur.studiary.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.babakbelur.studiary.core.domain.model.UserItem
import com.babakbelur.studiary.core.domain.repository.IAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: IAppRepository) : ViewModel() {

    val signIn = repository.signIn.asLiveData(viewModelScope.coroutineContext)
    val signUp = repository.signUp.asLiveData(viewModelScope.coroutineContext)

    fun signInRequest(username: String, password: String) = viewModelScope.launch {
        repository.signInRequest(username, password)
    }

    fun signUpRequest(name: String, username: String, password: String) = viewModelScope.launch {
        repository.signUpRequest(name, username, password)
    }
}