package com.babakbelur.studiary.core.di

import com.babakbelur.studiary.core.data.AppRepository
import com.babakbelur.studiary.core.data.remote.RemoteDataSource
import com.babakbelur.studiary.core.data.remote.network.ApiService
import com.babakbelur.studiary.core.domain.repository.IAppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesRemoteDataSource(apiService: ApiService): RemoteDataSource =
        RemoteDataSource(apiService)

    @Provides
    @Singleton
    fun providesAppRepository(remoteDataSource: RemoteDataSource): IAppRepository =
        AppRepository(remoteDataSource)
}