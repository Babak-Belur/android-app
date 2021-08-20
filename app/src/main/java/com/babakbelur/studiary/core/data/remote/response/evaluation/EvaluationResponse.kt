package com.babakbelur.studiary.core.data.remote.response.evaluation

import com.google.gson.annotations.SerializedName

data class EvaluationResponse(
    @SerializedName("data")
    val dataEvaluationResponse: DataEvaluationResponse,
)