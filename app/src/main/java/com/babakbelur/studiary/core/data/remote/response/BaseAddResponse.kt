package com.babakbelur.studiary.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class BaseAddResponse<T>(
    @SerializedName("data")
    val data: List<T>
)
