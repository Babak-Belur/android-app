package com.babakbelur.studiary.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.babakbelur.studiary.core.domain.usecase.IAppUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: IAppUseCase) : ViewModel() {

    fun getTargetByIdUser(idUser: Int) = useCase.getTargetByUserId(idUser).asLiveData()

    fun getTargetByIdTarget(idTarget: Int) = useCase.getTargetByTargetId(idTarget).asLiveData()
}