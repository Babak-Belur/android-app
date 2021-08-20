package com.babakbelur.studiary.core.data.remote.response.factor

import com.google.gson.annotations.SerializedName

data class DataFactorResponse(
    @SerializedName("factor")
    val factorResponse: List<FactorResponse>,

    @SerializedName("id_user")
    val idUser: Int = 0,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("role")
    val role: String? = null,

    @SerializedName("username")
    val username: String? = null
)