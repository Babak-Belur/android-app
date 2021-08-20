package com.babakbelur.studiary.core.data.remote.network

import com.babakbelur.studiary.core.data.remote.response.evaluation.EvaluationResponse
import com.babakbelur.studiary.core.data.remote.response.factor.FactorResponse
import com.babakbelur.studiary.core.data.remote.response.studyreport.StudyReportResponse
import com.babakbelur.studiary.core.data.remote.response.target.TargetResponse
import com.babakbelur.studiary.core.data.remote.response.user.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") userId: Int
    ): UserResponse

    /*@POST("users")
    suspend fun signUp(
        @Body name: String,
        @Body username: String,
        @Body password: String,
        @Body role: String
    )*/

    @GET("target/{id}")
    suspend fun getTargetById(
        @Path("id") userId: Int
    ): TargetResponse

    @GET("factor/{id}")
    suspend fun getFactorById(
        @Path("id") userId: Int
    ): FactorResponse

    @GET("study/{id}")
    suspend fun getStudyReportById(
        @Path("id") userId: Int
    ): StudyReportResponse

    @GET("evaluation/{id}")
    suspend fun getEvaluationById(
        @Path("id") userId: Int
    ): EvaluationResponse
}