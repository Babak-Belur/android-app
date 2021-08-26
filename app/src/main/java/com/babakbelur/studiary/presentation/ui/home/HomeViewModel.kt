package com.babakbelur.studiary.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.babakbelur.studiary.core.domain.repository.IAppRepository
import com.babakbelur.studiary.core.domain.usecase.IAppUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: IAppRepository) : ViewModel() {

    val listTarget = repository.listTarget.asLiveData(viewModelScope.coroutineContext)

    fun getAllTargetsUser(userId: Int) = viewModelScope.launch {
        repository.getAllTargetsUser(userId)
    }
}