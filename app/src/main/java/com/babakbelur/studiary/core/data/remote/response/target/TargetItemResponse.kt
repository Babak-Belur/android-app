package com.babakbelur.studiary.core.data.remote.response.target

import com.babakbelur.studiary.core.data.remote.response.course.CourseItemResponse
import com.google.gson.annotations.SerializedName

data class TargetItemResponse(
    @SerializedName("achived")
    val achieved: Int = 0,

    @SerializedName("course")
    val course: List<CourseItemResponse> = emptyList(),

    @SerializedName("g1")
    val g1: Int = 0,

    @SerializedName("grade_target")
    val gradeTarget: Int = 0,

    @SerializedName("id_course")
    val idCourse: Int = 0,

    @SerializedName("id_target")
    val idTarget: Int = 0,

    @SerializedName("target_time")
    val targetTime: String? = ""
)