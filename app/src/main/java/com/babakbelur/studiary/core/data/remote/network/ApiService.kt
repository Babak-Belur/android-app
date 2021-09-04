package com.babakbelur.studiary.core.data.remote.network

import com.babakbelur.studiary.core.data.remote.response.BaseAddResponse
import com.babakbelur.studiary.core.data.remote.response.BaseDeleteResponse
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
import com.babakbelur.studiary.core.utils.Constants
import retrofit2.http.*

interface ApiService {

    //Endpoint User
    @FormUrlEncoded
    @POST("login")
    suspend fun signInRequest(
        @Field("username") username: String,
        @Field("password") password: String
    ): BaseObjectResponse<DataLoginResponse>

    @FormUrlEncoded
    @POST("users")
    suspend fun signUpRequest(
        @Field("name") name: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("role") role: String = Constants.ADMIN
    ): BaseAddResponse<DataUserResponse>


    //Endpoint Target
    @GET("target/user/{id}")
    suspend fun getTargetByUserId(
        @Path("id") userId: Int
    ): BaseObjectResponse<DataTargetResponse>

    @GET("target/{id}")
    suspend fun getTargetByIdTarget(
        @Path("id") targetId: Int
    ): TargetResponse

    @FormUrlEncoded
    @POST("target")
    suspend fun addTarget(
        @Field("id_user") userId: Int,
        @Field("id_course") courseId: Int,
        @Field("g1") preTestScore: Int,
        @Field("grade_target") targetScore: Int,
        @Field("target_time") targetTime: String,
        @Field("achived") achieved: Int = 0
    ): BaseAddResponse<TargetItemResponse>

    @DELETE("target/{id}")
    suspend fun deleteTarget(
        @Path("id") idUser: Int
    ): BaseDeleteResponse


    //Endpoint Predict Score
    @GET("predict/{id}")
    suspend fun getPredictedScore(
        @Path("id") evaluationId: Int
    ): BaseListResponse<PredictResponse>


    //Endpoint Evaluation
    @GET("evaluation/{id}")
    suspend fun getDetailEvaluation(
        @Path("id") evaluationId: Int
    ): BaseObjectResponse<EvaluationItemResponse>

    @GET("evaluation/user/{id}")
    suspend fun getAllUserEvaluations(
        @Path("id") userId: Int
    ): BaseObjectResponse<DataEvaluationResponse>

    @FormUrlEncoded
    @POST("evaluation")
    suspend fun addEvaluation(
        @Field("id_user") userId: Int,
        @Field("date") date: String,
        @Field("grade") evaluationScore: Int,
        @Field("study_time") studyTime: Int,
        @Field("freetime") freeTime: Int,
        @Field("id_target") targetId: Int
    ): BaseAddResponse<EvaluationItemResponse>

    @DELETE("evaluation/{id}")
    suspend fun deleteEvaluation(
        @Path("id") idEvaluation: Int
    ): BaseDeleteResponse


    //Endpoint Course
    @GET("course")
    suspend fun getAllCourses(): BaseListResponse<CourseItemResponse>

    @GET("course/{id}")
    suspend fun getCourseById(
        @Path("id") idCourse: Int
    ): BaseObjectResponse<CourseItemResponse>

    @POST("course")
    suspend fun addCourse(
        @Field("course_name") subject: String,
        @Field("description") description: String
    ): BaseAddResponse<CourseItemResponse>

    @DELETE("course/{id}")
    suspend fun deleteCourse(
        @Path("id") idCourse: Int
    ): BaseDeleteResponse

}