package com.babakbelur.studiary.core.data.remote.response.user

import com.google.gson.annotations.SerializedName

data class DataLoginResponse(
    @SerializedName("access_token")
    val accessToken: String? = "",

    @SerializedName("data")
    val data: List<DataUserResponse> = emptyList(),

    @SerializedName("refresh_token")
    val refreshToken: String? = ""
)