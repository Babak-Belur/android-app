package com.babakbelur.studiary.core.data.remote.response.user

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val dataUserResponse: DataUserResponse
)