package com.babakbelur.studiary.core.domain.model

import com.babakbelur.studiary.core.data.remote.response.studyreport.StudyReportItemResponse

data class StudyReport(
    val idUser: Int = 0,
    val name: String? = null,
    val role: String? = null,
    val studyReportItemResponse: List<StudyReportItem>,
    val username: String? = null
)

data class StudyReportItem(
    val date: String? = null,
    val idCourse: Int = 0,
    val idStudyReport: Int = 0,
    val idUser: Int = 0,
    val studyTime: Int = 0
)
