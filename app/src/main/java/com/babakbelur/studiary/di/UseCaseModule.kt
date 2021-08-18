package com.babakbelur.studiary.di

import com.babakbelur.studiary.core.domain.repository.IAppRepository
import com.babakbelur.studiary.core.domain.usecase.AppInteractors
import com.babakbelur.studiary.core.domain.usecase.IAppUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesAppUseCase(appRepository: IAppRepository): IAppUseCase =
        AppInteractors(appRepository)
}