package com.babakbelur.studiary.core.data.remote.response.user

import com.google.gson.annotations.SerializedName

data class DetailUserResponse(
    @SerializedName("age")
    val age: Int = 0,

    @SerializedName("fjob")
    val fatherJob: String? = null,

    @SerializedName("gender")
    val gender: String? = null,

    @SerializedName("id_detail_user")
    val idDetailUser: Int = 0,

    @SerializedName("internet")
    val internet: String? = null,

    @SerializedName("mjob")
    val motherJob: String? = null,

    @SerializedName("pstatus")
    val pStatus: String? = null
)