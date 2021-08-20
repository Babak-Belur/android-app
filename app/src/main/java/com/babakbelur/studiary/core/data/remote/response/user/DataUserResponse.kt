package com.babakbelur.studiary.core.data.remote.response.user

import com.google.gson.annotations.SerializedName

data class DataUserResponse(
    @SerializedName("detail_user")
    val detailUserResponse: List<DetailUserResponse>,

    @SerializedName("id_user")
    val idUser: Int = 0,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("role")
    val role: String? = null,

    @SerializedName("username")
    val username: String? = null
)