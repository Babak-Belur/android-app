package com.babakbelur.studiary.core.data

import com.babakbelur.studiary.core.data.remote.RemoteDataSource
import com.babakbelur.studiary.core.domain.repository.IAppRepository
import javax.inject.Inject

class AppRepository @Inject constructor(remoteDataSource: RemoteDataSource): IAppRepository {
}