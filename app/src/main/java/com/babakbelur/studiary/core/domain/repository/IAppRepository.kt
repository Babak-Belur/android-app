package com.babakbelur.studiary.core.domain.repository

import com.babakbelur.studiary.core.data.ResultState
import com.babakbelur.studiary.core.data.remote.response.BaseAddResponse
import com.babakbelur.studiary.core.domain.model.ResultModel
import com.babakbelur.studiary.core.domain.model.Target
import com.babakbelur.studiary.core.domain.model.User
import com.babakbelur.studiary.core.domain.model.UserItem
import kotlinx.coroutines.flow.StateFlow

interface IAppRepository {

    val signIn: StateFlow<ResultState<ResultModel<User>>>
    val signUp: StateFlow<ResultState<BaseAddResponse<UserItem>>>
    val listTarget: StateFlow<ResultState<ResultModel<Target>>>

    suspend fun signInRequest(username: String, password: String)

    suspend fun signUpRequest(name: String, username: String, password: String)

    suspend fun getAllTargetsUser(userId: Int)
}