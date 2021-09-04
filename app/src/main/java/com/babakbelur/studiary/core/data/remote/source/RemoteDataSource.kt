package com.babakbelur.studiary.core.data.remote.source

import com.babakbelur.studiary.core.data.remote.network.ApiService
import com.babakbelur.studiary.core.data.remote.response.BaseAddResponse
import com.babakbelur.studiary.core.data.remote.response.BaseListResponse
import com.babakbelur.studiary.core.data.remote.response.BaseObjectResponse
import com.babakbelur.studiary.core.data.remote.response.course.CourseItemResponse
import com.babakbelur.studiary.core.data.remote.response.evaluation.DataEvaluationResponse
import com.babakbelur.studiary.core.data.remote.response.evaluation.EvaluationItemResponse
import com.babakbelur.studiary.core.data.remote.response.predict.PredictResponse
import com.babakbelur.studiary.core.data.remote.response.target.DataTargetResponse
import com.babakbelur.studiary.core.data.remote.response.target.TargetItemResponse
import com.babakbelur.studiary.core.data.remote.response.target.TargetResponse
import com.babakbelur.studiary.core.data.remote.response.user.DataLoginResponse
import com.babakbelur.studiary.core.data.remote.response.user.DataUserResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) : IRemoteDataSource {

    override suspend fun signInRequest(
        username: String,
        password: String
    ): BaseObjectResponse<DataLoginResponse> {
        return apiService.signInRequest(username, password)
    }

    override suspend fun signUpRequest(
        name: String,
        username: String,
        password: String
    ): BaseAddResponse<DataUserResponse> {
        return apiService.signUpRequest(name, username, password)
    }

    override suspend fun getAllTargetsUser(userId: Int): BaseObjectResponse<DataTargetResponse> {
        return apiService.getTargetByUserId(userId)
    }

    override suspend fun getDetailTarget(targetId: Int): TargetResponse {
        return apiService.getTargetByIdTarget(targetId)
    }

    override suspend fun getPredictedScore(evaluationId: Int): BaseListResponse<PredictResponse> {
        return apiService.getPredictedScore(evaluationId)
    }

    override suspend fun addTarget(
        userId: Int,
        courseId: Int,
        preTestScore: Int,
        targetScore: Int,
        targetTime: String,
    ): BaseAddResponse<TargetItemResponse> {
        return apiService.addTarget(
            userId,
            courseId,
            preTestScore,
            targetScore,
            targetTime,
        )
    }

    override suspend fun getAllCourses(): BaseListResponse<CourseItemResponse> {
        return apiService.getAllCourses()
    }

    override suspend fun addCourse(
        subject: String,
        description: String
    ): BaseAddResponse<CourseItemResponse> {
        return apiService.addCourse(subject, description)
    }

    override suspend fun getAllUserEvaluations(userId: Int): BaseObjectResponse<DataEvaluationResponse> {
        return apiService.getAllUserEvaluations(userId)
    }

    override suspend fun addEvaluation(
        userId: Int,
        date: String,
        evaluationScore: Int,
        studyTime: Int,
        freeTime: Int,
        targetId: Int
    ): BaseAddResponse<EvaluationItemResponse> {
        return apiService.addEvaluation(
            userId,
            date,
            evaluationScore,
            studyTime,
            freeTime,
            targetId
        )
    }
}