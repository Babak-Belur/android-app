package com.babakbelur.studiary.core.data.remote.response.evaluation

import com.babakbelur.studiary.core.data.remote.response.target.TargetItemResponse
import com.google.gson.annotations.SerializedName

data class EvaluationItemResponse(
    @SerializedName("date")
    val date: String? = "",

    @SerializedName("freetime")
    val freeTime: String? = "",

    @SerializedName("grade")
    val grade: Int = 0,

    @SerializedName("id_evaluation")
    val idEvaluation: Int = 0,

    @SerializedName("study_time")
    val studyTime: String? = "",

    @SerializedName("target")
    val target: List<TargetItemResponse>
)