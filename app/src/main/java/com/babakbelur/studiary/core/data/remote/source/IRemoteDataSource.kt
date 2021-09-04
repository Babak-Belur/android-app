package com.babakbelur.studiary.core.data.remote.source

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
import dagger.multibindings.IntoMap

interface IRemoteDataSource {

    suspend fun signInRequest(
        username: String,
        password: String
    ): BaseObjectResponse<DataLoginResponse>

    suspend fun signUpRequest(
        name: String,
        username: String,
        password: String
    ): BaseAddResponse<DataUserResponse>

    suspend fun getAllTargetsUser(userId: Int): BaseObjectResponse<DataTargetResponse>

    suspend fun getDetailTarget(targetId: Int): TargetResponse

    suspend fun getPredictedScore(evaluationId: Int): BaseListResponse<PredictResponse>

    suspend fun addTarget(
        userId: Int,
        courseId: Int,
        preTestScore: Int,
        targetScore: Int,
        targetTime: String,
    ): BaseAddResponse<TargetItemResponse>

    suspend fun getAllCourses(): BaseListResponse<CourseItemResponse>

    suspend fun addCourse(
        subject: String,
        description: String
    ): BaseAddResponse<CourseItemResponse>

    suspend fun getAllUserEvaluations(
        userId: Int
    ): BaseObjectResponse<DataEvaluationResponse>

    suspend fun addEvaluation(
        userId: Int,
        date: String,
        evaluationScore: Int,
        studyTime: Int,
        freeTime: Int,
        targetId: Int
    ): BaseAddResponse<EvaluationItemResponse>
}