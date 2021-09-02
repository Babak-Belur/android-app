package com.babakbelur.studiary.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class BaseObjectResponse<T>(
    @SerializedName("data")
    val `data`: T
)

data class BaseListResponse<T>(
    val data: List<T>
)