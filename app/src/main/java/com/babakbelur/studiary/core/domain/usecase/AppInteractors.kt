package com.babakbelur.studiary.core.domain.usecase

import com.babakbelur.studiary.core.domain.repository.IAppRepository
import javax.inject.Inject

class AppInteractors @Inject constructor(private val appRepository: IAppRepository): IAppUseCase {

}