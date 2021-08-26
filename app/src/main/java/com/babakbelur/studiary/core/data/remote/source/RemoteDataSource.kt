package com.babakbelur.studiary.core.data.remote.source

import com.babakbelur.studiary.core.data.remote.network.ApiService
import com.babakbelur.studiary.core.data.remote.response.BaseAddResponse
import com.babakbelur.studiary.core.data.remote.response.BaseResponse
import com.babakbelur.studiary.core.data.remote.response.target.DataTargetResponse
import com.babakbelur.studiary.core.data.remote.response.user.DataLoginResponse
import com.babakbelur.studiary.core.data.remote.response.user.DataUserResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) : IRemoteDataSource {

    override suspend fun signInRequest(
        username: String,
        password: String
    ): BaseResponse<DataLoginResponse> {
        return apiService.signInRequest(username, password)
    }

    override suspend fun signUpRequest(
        name: String,
        username: String,
        password: String
    ): BaseAddResponse<DataUserResponse> {
        return apiService.signUpRequest(name, username, password)
    }

    override suspend fun getAllTargetsUser(userId: Int): BaseResponse<DataTargetResponse> {
        return apiService.getTargetByUserId(userId)
    }
}