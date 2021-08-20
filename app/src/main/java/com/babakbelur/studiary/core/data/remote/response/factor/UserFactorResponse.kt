package com.babakbelur.studiary.core.data.remote.response.factor

import com.google.gson.annotations.SerializedName

data class UserFactorResponse(
    @SerializedName("data")
    val dataFactorResponse: DataFactorResponse,
)