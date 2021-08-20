package com.babakbelur.studiary.core.data.remote.response.factor

import com.google.gson.annotations.SerializedName

data class FactorResponse(
    @SerializedName("extra_paid_course")
    val extraPaidCourse: String,

    @SerializedName("extracurricular")
    val extracurricular: String,

    @SerializedName("freetime")
    val freeTime: Int,

    @SerializedName("g1")
    val g1: Int,

    @SerializedName("health")
    val health: String,

    @SerializedName("id_factor")
    val idFactor: Int,

    @SerializedName("id_user")
    val idUser: Int,

    @SerializedName("study_time")
    val studyTime: Int,

    @SerializedName("take_higher_education")
    val takeHigherEducation: String
)