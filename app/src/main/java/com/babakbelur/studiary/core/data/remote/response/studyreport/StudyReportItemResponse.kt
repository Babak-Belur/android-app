package com.babakbelur.studiary.core.data.remote.response.studyreport

import com.google.gson.annotations.SerializedName

data class StudyReportItemResponse(
    @SerializedName("date")
    val date: String? = null,

    @SerializedName("id_course")
    val idCourse: Int = 0,

    @SerializedName("id_study_report")
    val idStudyReport: Int = 0,

    @SerializedName("id_user")
    val idUser: Int = 0,

    @SerializedName("study_time")
    val studyTime: Int = 0
)