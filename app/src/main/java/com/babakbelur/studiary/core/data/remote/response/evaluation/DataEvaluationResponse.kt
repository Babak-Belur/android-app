package com.babakbelur.studiary.core.data.remote.response.evaluation

import com.google.gson.annotations.SerializedName

data class DataEvaluationResponse(
    @SerializedName("evaluation")
    val evaluation: List<EvaluationItemResponse> = emptyList(),

    @SerializedName("id_user")
    val idUser: Int = 0,

    @SerializedName("name")
    val name: String? = "",

    @SerializedName("role")
    val role: String? = "",

    @SerializedName("username")
    val username: String? = ""
)