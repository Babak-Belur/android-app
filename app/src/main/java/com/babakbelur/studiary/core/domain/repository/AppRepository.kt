package com.babakbelur.studiary.core.domain.repository

import com.babakbelur.studiary.core.data.ResultState
import com.babakbelur.studiary.core.data.remote.response.BaseAddResponse
import com.babakbelur.studiary.core.data.remote.source.RemoteDataSource
import com.babakbelur.studiary.core.domain.model.ResultModel
import com.babakbelur.studiary.core.domain.model.Target
import com.babakbelur.studiary.core.domain.model.User
import com.babakbelur.studiary.core.domain.model.UserItem
import com.babakbelur.studiary.core.utils.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalCoroutinesApi
@Suppress("UNCHECKED_CAST")
class AppRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    IAppRepository {

    private val _signIn: MutableStateFlow<ResultState<ResultModel<User>>> = idle()
    private val _signUp: MutableStateFlow<ResultState<BaseAddResponse<UserItem>>> = idle()
    private val _listTarget: MutableStateFlow<ResultState<ResultModel<Target>>> = idle()

    override val signIn: StateFlow<ResultState<ResultModel<User>>> = _signIn
    override val signUp: StateFlow<ResultState<BaseAddResponse<UserItem>>> = _signUp
    override val listTarget: StateFlow<ResultState<ResultModel<Target>>> = _listTarget

    override suspend fun signInRequest(username: String, password: String) {
        fetch {
            remoteDataSource.signInRequest(username, password)
        }.map { result ->
            Mapper.mapResult(result) {
                this.toResultModelOfUser()
            }
        }.collect {
            _signIn.value = it
        }
    }

    override suspend fun signUpRequest(
        name: String,
        username: String,
        password: String
    ) {
        fetch {
            remoteDataSource.signUpRequest(name, username, password)
        }.map { result ->
            Mapper.mapResult(result) {
                this.toBaseResponseOfListUserItem()
            }
        }.collect { result ->
            _signUp.value = result
        }
    }

    override suspend fun getAllTargetsUser(userId: Int) {
        fetch {
            remoteDataSource.getAllTargetsUser(userId)
        }.map { result ->
            Mapper.mapResult(result) {
                this.toResultModelOfTarget()
            }
        }.collect { result ->
            _listTarget.value = result
        }
    }
}