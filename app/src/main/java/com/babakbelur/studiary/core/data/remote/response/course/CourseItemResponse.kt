package com.babakbelur.studiary.core.data.remote.response.course

import com.google.gson.annotations.SerializedName

data class CourseItemResponse(
    @SerializedName("course_name")
    val courseName: String? = "",

    @SerializedName("description")
    val description: String? = "",

    @SerializedName("id_course")
    val idCourse: Int = 0
)