package com.babakbelur.studiary.core.data.remote.response.target

import com.google.gson.annotations.SerializedName

data class TargetResponse(
    @SerializedName("achived")
    val achieved: Boolean = false,

    @SerializedName("grade")
    val grade: Int = 0,

    @SerializedName("id_course")
    val idCourse: Int,

    @SerializedName("id_target")
    val idTarget: Int,

    @SerializedName("target_time")
    val targetTime: Int
)