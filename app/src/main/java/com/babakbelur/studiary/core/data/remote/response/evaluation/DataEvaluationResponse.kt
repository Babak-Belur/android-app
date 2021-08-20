package com.babakbelur.studiary.core.data.remote.response.evaluation

import com.google.gson.annotations.SerializedName

data class DataEvaluationResponse(
    @SerializedName("evaluation")
    val evaluationItemResponse: List<EvaluationItemResponse>,

    @SerializedName("id_user")
    val idUser: Int = 0,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("role")
    val role: String? = null,

    @SerializedName("username")
    val username: String? = null
)