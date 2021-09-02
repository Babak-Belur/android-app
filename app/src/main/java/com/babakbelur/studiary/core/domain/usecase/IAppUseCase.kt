package com.babakbelur.studiary.core.domain.usecase

import com.babakbelur.studiary.core.data.ResultState
import com.babakbelur.studiary.core.domain.model.*
import com.babakbelur.studiary.core.domain.model.Target
import kotlinx.coroutines.flow.StateFlow

interface IAppUseCase {

    val signIn: StateFlow<ResultState<ResultModel<User>>>
    val signUp: StateFlow<ResultState<ResultAddModel<UserItem>>>
    val listTarget: StateFlow<ResultState<ResultModel<Target>>>
    val detailTarget: StateFlow<ResultState<ResultTarget>>
    val predictedScore: StateFlow<ResultState<ResultListModel<Predict>>>
    val addTarget: StateFlow<ResultState<ResultAddModel<TargetItem>>>
    val addCourse: StateFlow<ResultState<ResultAddModel<Course>>>
    val listCourses: StateFlow<ResultState<ResultListModel<Course>>>

    suspend fun signInRequest(username: String, password: String)

    suspend fun signUpRequest(name: String, username: String, password: String)

    suspend fun getAllTargetsUser(userId: Int)

    suspend fun getDetailTarget(targetId: Int)

    suspend fun getPredictedScore(evaluationId: Int)

    suspend fun addTarget(
        userId: Int,
        courseId: Int,
        preTestScore: Int,
        targetScore: Int,
        targetTime: String,
    )

    suspend fun getAllCourses()

    suspend fun addCourse(subject: String, description: String)
}