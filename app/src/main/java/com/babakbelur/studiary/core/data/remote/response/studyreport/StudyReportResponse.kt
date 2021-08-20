package com.babakbelur.studiary.core.data.remote.response.studyreport

import com.google.gson.annotations.SerializedName

data class StudyReportResponse(
    @SerializedName("data")
    val dataStudyReportResponse: DataStudyReportResponse,
)