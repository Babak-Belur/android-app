package com.babakbelur.studiary.core.data.remote.response.target

import com.google.gson.annotations.SerializedName

data class DataTargetResponse(
    @SerializedName("id_user")
    val idUser: Int = 0,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("role")
    val role: String? = null,

    @SerializedName("target")
    val targetResponse: List<TargetResponse>,

    @SerializedName("username")
    val username: String? = null
)