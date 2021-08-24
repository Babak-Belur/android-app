package com.babakbelur.studiary.core.data.remote.response.user

import com.google.gson.annotations.SerializedName

data class DataUserResponse(
    @SerializedName("id_user")
    val idUser: Int = 0,

    @SerializedName("name")
    val name: String? = "",

    @SerializedName("role")
    val role: String? = "",

    @SerializedName("username")
    val username: String? = "",

    @SerializedName("password")
    val password: String? = ""
)