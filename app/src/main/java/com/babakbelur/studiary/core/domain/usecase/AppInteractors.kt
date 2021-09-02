package com.babakbelur.studiary.core.domain.usecase

import com.babakbelur.studiary.core.data.ResultState
import com.babakbelur.studiary.core.domain.model.*
import com.babakbelur.studiary.core.domain.model.Target
import com.babakbelur.studiary.core.domain.repository.IAppRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AppInteractors @Inject constructor(private val appRepository: IAppRepository) : IAppUseCase {

    override val signIn: StateFlow<ResultState<ResultModel<User>>>
        get() = appRepository.signIn

    override val signUp: StateFlow<ResultState<ResultAddModel<UserItem>>>
        get() = appRepository.signUp

    override val listTarget: StateFlow<ResultState<ResultModel<Target>>>
        get() = appRepository.listTarget

    override val detailTarget: StateFlow<ResultState<ResultTarget>>
        get() = appRepository.detailTarget

    override val predictedScore: StateFlow<ResultState<ResultListModel<Predict>>>
        get() = appRepository.predictedScore

    override val addTarget: StateFlow<ResultState<ResultAddModel<TargetItem>>>
        get() = appRepository.addTarget

    override val addCourse: StateFlow<ResultState<ResultAddModel<Course>>>
        get() = appRepository.addCourse

    override val listCourses: StateFlow<ResultState<ResultListModel<Course>>>
        get() = appRepository.listCourses

    override suspend fun signInRequest(username: String, password: String) {
        return appRepository.signInRequest(username, password)
    }

    override suspend fun signUpRequest(name: String, username: String, password: String) {
        return appRepository.signUpRequest(name, username, password)
    }

    override suspend fun getAllTargetsUser(userId: Int) {
        return appRepository.getAllTargetsUser(userId)
    }

    override suspend fun getDetailTarget(targetId: Int) {
        return appRepository.getDetailTarget(targetId)
    }

    override suspend fun getPredictedScore(evaluationId: Int) {
        return appRepository.getPredictedScore(evaluationId)
    }

    override suspend fun addTarget(
        userId: Int,
        courseId: Int,
        preTestScore: Int,
        targetScore: Int,
        targetTime: String
    ) {
        return appRepository.addTarget(userId, courseId, preTestScore, targetScore, targetTime)
    }

    override suspend fun getAllCourses() {
        return appRepository.getAllCourses()
    }

    override suspend fun addCourse(subject: String, description: String) {
        return appRepository.addCourse(subject, description)
    }
}