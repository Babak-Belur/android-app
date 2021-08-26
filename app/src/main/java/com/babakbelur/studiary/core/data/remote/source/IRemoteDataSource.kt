package com.babakbelur.studiary.core.data.remote.source

import com.babakbelur.studiary.core.data.remote.response.BaseAddResponse
import com.babakbelur.studiary.core.data.remote.response.BaseResponse
import com.babakbelur.studiary.core.data.remote.response.target.DataTargetResponse
import com.babakbelur.studiary.core.data.remote.response.user.DataLoginResponse
import com.babakbelur.studiary.core.data.remote.response.user.DataUserResponse

interface IRemoteDataSource {

    suspend fun signInRequest(username: String, password: String): BaseResponse<DataLoginResponse>

    suspend fun signUpRequest(
        name: String,
        username: String,
        password: String
    ): BaseAddResponse<DataUserResponse>

    suspend fun getAllTargetsUser(userId: Int): BaseResponse<DataTargetResponse>
}