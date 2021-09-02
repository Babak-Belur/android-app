package com.babakbelur.studiary.core.domain.repository

import com.babakbelur.studiary.core.data.ResultState
import com.babakbelur.studiary.core.data.remote.source.RemoteDataSource
import com.babakbelur.studiary.core.domain.model.*
import com.babakbelur.studiary.core.domain.model.Target
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
    private val _signUp: MutableStateFlow<ResultState<ResultAddModel<UserItem>>> = idle()
    private val _listTarget: MutableStateFlow<ResultState<ResultModel<Target>>> = idle()
    private val _detailTarget: MutableStateFlow<ResultState<ResultTarget>> = idle()
    private val _predictedScore: MutableStateFlow<ResultState<ResultListModel<Predict>>> = idle()
    private val _addTarget: MutableStateFlow<ResultState<ResultAddModel<TargetItem>>> = idle()
    private val _addCourse: MutableStateFlow<ResultState<ResultAddModel<Course>>> = idle()
    private val _listCourses: MutableStateFlow<ResultState<ResultListModel<Course>>> = idle()

    override val signIn: StateFlow<ResultState<ResultModel<User>>> = _signIn
    override val signUp: StateFlow<ResultState<ResultAddModel<UserItem>>> = _signUp
    override val listTarget: StateFlow<ResultState<ResultModel<Target>>> = _listTarget
    override val detailTarget: StateFlow<ResultState<ResultTarget>> = _detailTarget
    override val predictedScore: StateFlow<ResultState<ResultListModel<Predict>>> = _predictedScore
    override val addTarget: StateFlow<ResultState<ResultAddModel<TargetItem>>> = _addTarget
    override val listCourses: StateFlow<ResultState<ResultListModel<Course>>> = _listCourses
    override val addCourse: StateFlow<ResultState<ResultAddModel<Course>>> = _addCourse

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
                this.toResultAddModelOfListUserItem()
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

    override suspend fun getDetailTarget(targetId: Int) {
        fetch {
            remoteDataSource.getDetailTarget(targetId)
        }.map { result ->
            Mapper.mapResult(result) {
                this.toResultTarget()
            }
        }.collect { result ->
            _detailTarget.value = result
        }
    }

    override suspend fun getPredictedScore(evaluationId: Int) {
        fetch {
            remoteDataSource.getPredictedScore(evaluationId)
        }.map { result ->
            Mapper.mapResult(result) {
                this.toResultListModelOfPredict()
            }
        }.collect { result ->
            _predictedScore.value = result
        }
    }

    override suspend fun addTarget(
        userId: Int,
        courseId: Int,
        preTestScore: Int,
        targetScore: Int,
        targetTime: String,
    ) {
        fetch {
            remoteDataSource.addTarget(
                courseId,
                courseId,
                preTestScore,
                targetScore,
                targetTime
            )
        }.map { result ->
            Mapper.mapResult(result) {
                this.toResultAddModelOfListTargetItem()
            }
        }.collect { result ->
            _addTarget.value = result
        }
    }

    override suspend fun getAllCourses() {
        fetch {
            remoteDataSource.getAllCourses()
        }.map { result ->
            Mapper.mapResult(result) {
                this.toResultListModelOfCourse()
            }
        }.collect { result ->
            _listCourses.value = result
        }
    }

    override suspend fun addCourse(subject: String, description: String) {
        fetch {
            remoteDataSource.addCourse(subject, description)
        }.map { result ->
            Mapper.mapResult(result) {
                this.toResultAddModelOfListCourse()
            }
        }.collect { result ->
            _addCourse.value = result
        }
    }

}