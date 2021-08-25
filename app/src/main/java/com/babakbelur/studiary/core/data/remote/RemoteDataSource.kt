package com.babakbelur.studiary.core.data.remote

import com.babakbelur.studiary.core.data.remote.network.ApiResponse
import com.babakbelur.studiary.core.data.remote.network.ApiService
import com.babakbelur.studiary.core.data.remote.response.BaseDeleteResponse
import com.babakbelur.studiary.core.data.remote.response.course.CourseItemResponse
import com.babakbelur.studiary.core.data.remote.response.evaluation.EvaluationItemResponse
import com.babakbelur.studiary.core.data.remote.response.target.DataTargetResponse
import com.babakbelur.studiary.core.data.remote.response.target.TargetItemResponse
import com.babakbelur.studiary.core.data.remote.response.user.DataLoginResponse
import com.babakbelur.studiary.core.data.remote.response.user.DataUserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) :
    HandleApiResponse() {

    suspend fun signIn(user: DataUserResponse): Flow<ApiResponse<DataLoginResponse>> {
        val response = apiService.signIn(user)
        return handleGetResponse(response)
    }

    suspend fun signUp(user: DataUserResponse): Flow<ApiResponse<DataUserResponse>> {
        val response = apiService.signUp(user)
        return handleGetResponse(response)
    }

    suspend fun getTargetByTargetId(idTarget: Int): Flow<ApiResponse<List<TargetItemResponse>>> {
        val getResponse = apiService.getTargetByIdTarget(idTarget)
        return flow {
            val data = getResponse.data
            if (data.isNotEmpty()) {
                emit(ApiResponse.Success(data))
            } else {
                emit(ApiResponse.Empty)
            }
        }.catch { e ->
            emit(ApiResponse.Error(e.message.toString()))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTargetByUserId(idUser: Int): Flow<ApiResponse<List<TargetItemResponse>>> {
        val getResponse = apiService.getTargetByUserId(idUser)
        return flow {
            if (getResponse.data.target.isNotEmpty()) {
                emit(ApiResponse.Success(getResponse.data.target))
            } else {
                emit(ApiResponse.Empty)
            }
        }.catch { e ->
            emit(ApiResponse.Error(e.message.toString()))
        }
    }

    suspend fun addTarget(target: TargetItemResponse): Flow<ApiResponse<List<TargetItemResponse>>> {
        val postResponse = apiService.addTarget(target)
        return handlePostResponse(postResponse)
    }

    suspend fun deleteTarget(idUser: Int): Flow<ApiResponse<BaseDeleteResponse>> {
        val deleteResponse = apiService.deleteTarget(idUser)
        return handleDeleteResponse(deleteResponse)
    }

    suspend fun getEvaluationById(idUser: Int): Flow<ApiResponse<EvaluationItemResponse>> {
        val getResponse = apiService.getEvaluationById(idUser)
        return handleGetResponse(getResponse)
    }

    suspend fun addEvaluation(evaluation: EvaluationItemResponse): Flow<ApiResponse<List<EvaluationItemResponse>>> {
        val postResponse = apiService.addEvaluation(evaluation)
        return handlePostResponse(postResponse)
    }

    suspend fun deleteEvaluation(idEvaluation: Int): Flow<ApiResponse<BaseDeleteResponse>> {
        val deleteResponse = apiService.deleteEvaluation(idEvaluation)
        return handleDeleteResponse(deleteResponse)
    }

    suspend fun getCourseById(idCourse: Int): Flow<ApiResponse<CourseItemResponse>> {
        val getResponse = apiService.getCourseById(idCourse)
        return handleGetResponse(getResponse)
    }

    suspend fun addCourse(course: CourseItemResponse): Flow<ApiResponse<List<CourseItemResponse>>> {
        val postResponse = apiService.addCourse(course)
        return handlePostResponse(postResponse)
    }

    suspend fun deleteCourse(idCourse: Int): Flow<ApiResponse<BaseDeleteResponse>> {
        val deleteResponse = apiService.deleteCourse(idCourse)
        return handleDeleteResponse(deleteResponse)
    }

}