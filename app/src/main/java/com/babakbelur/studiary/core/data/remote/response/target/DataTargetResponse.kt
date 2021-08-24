package com.babakbelur.studiary.core.data.remote.response.target

import com.google.gson.annotations.SerializedName

data class DataTargetResponse(
    @SerializedName("id_user")
    val idUser: Int = 0,

    @SerializedName("name")
    val name: String? = "",

    @SerializedName("role")
    val role: String? = "",

    @SerializedName("target")
    val target: List<TargetItemResponse>,

    @SerializedName("username")
    val username: String? = ""
)