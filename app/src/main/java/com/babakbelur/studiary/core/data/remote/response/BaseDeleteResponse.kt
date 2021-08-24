package com.babakbelur.studiary.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class BaseDeleteResponse(
    @SerializedName("data")
    val data: String? = "",

    @SerializedName("message")
    val message: String? = ""
)
