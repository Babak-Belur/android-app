package com.babakbelur.studiary.core.data.remote.response.studyreport

import com.google.gson.annotations.SerializedName

data class DataStudyReportResponse(
    @SerializedName("id_user")
    val idUser: Int = 0,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("role")
    val role: String? = null,

    @SerializedName("study_report")
    val studyReportItemResponse: List<StudyReportItemResponse>,

    @SerializedName("username")
    val username: String? = null
)