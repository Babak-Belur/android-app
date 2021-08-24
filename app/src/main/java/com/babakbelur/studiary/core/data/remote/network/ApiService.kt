package com.babakbelur.studiary.core.data.remote.network

import com.babakbelur.studiary.core.data.remote.response.BaseAddResponse
import com.babakbelur.studiary.core.data.remote.response.BaseDeleteResponse
import com.babakbelur.studiary.core.data.remote.response.BaseResponse
import com.babakbelur.studiary.core.data.remote.response.course.CourseItemResponse
import com.babakbelur.studiary.core.data.remote.response.evaluation.EvaluationItemResponse
import com.babakbelur.studiary.core.data.remote.response.target.TargetItemResponse
import com.babakbelur.studiary.core.data.remote.response.user.DataLoginResponse
import com.babakbelur.studiary.core.data.remote.response.user.DataUserResponse
import retrofit2.http.*

interface ApiService {

    //Endpoint User
    @POST("login")
    suspend fun signIn(
        @Body() user: DataUserResponse
    ): BaseResponse<DataLoginResponse>

    @POST("users")
    suspend fun signUp(
        @Body() user: DataUserResponse
    ): BaseResponse<DataUserResponse>


    //Endpoint Target
    @GET("target/{id}")
    suspend fun getTargetById(
        @Path("id") userId: Int
    ): BaseResponse<TargetItemResponse>

    @POST("target")
    suspend fun addTarget(
        @Body target: TargetItemResponse
    ): BaseAddResponse<TargetItemResponse>

    @DELETE("target/{id}")
    suspend fun deleteTarget(
        @Path("id") idUser: Int
    ): BaseDeleteResponse


    //Endpoint Evaluation
    @GET("evaluation/user/{id}")
    suspend fun getEvaluationById(
        @Path("id") userId: Int
    ): BaseResponse<EvaluationItemResponse>

    @POST("evaluation")
    suspend fun addEvaluation(
        @Body evaluation: EvaluationItemResponse
    ): BaseAddResponse<EvaluationItemResponse>

    @DELETE("evaluation/{id}")
    suspend fun deleteEvaluation(
        @Path("id") idUser: Int
    ): BaseDeleteResponse


    //Endpoint Course
    @GET("course/{id}")
    suspend fun getCourseById(
        @Path("id") idCourse: Int
    ): BaseResponse<CourseItemResponse>

    @POST("course")
    suspend fun addCourse(
        @Body course: CourseItemResponse
    ): BaseAddResponse<CourseItemResponse>

    @DELETE("course/{id}")
    suspend fun deleteCourse(
        @Path("id") idCourse: Int
    ): BaseDeleteResponse

}