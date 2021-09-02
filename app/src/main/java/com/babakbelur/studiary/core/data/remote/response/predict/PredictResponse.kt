package com.babakbelur.studiary.core.data.remote.response.predict

import com.google.gson.annotations.SerializedName

data class PredictResponse(
    @SerializedName("freetime")
    val freeTime: String? = null,

    @SerializedName("g1")
    val preTestScore: Int = 0,

    @SerializedName("grade")
    val grade: Int = 0,

    @SerializedName("predict_grade")
    val predictedScore: Double = 0.0,

    @SerializedName("study_time")
    val studyTime: String? = null
)