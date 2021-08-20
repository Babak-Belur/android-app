package com.babakbelur.studiary.core.data.remote.response.target

import com.google.gson.annotations.SerializedName

data class UserTargetResponse(
    @SerializedName("data")
    val dataTargetResponse: DataTargetResponse,
)