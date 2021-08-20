package com.babakbelur.studiary.core.data.remote.response.evaluation

import com.google.gson.annotations.SerializedName

data class EvaluationItemResponse(
    @SerializedName("grade")
    val grade: Int = 0,

    @SerializedName("id_course")
    val idCourse: Int = 0,

    @SerializedName("id_evaluation")
    val idEvaluation: Int = 0,

    @SerializedName("id_user")
    val idUser: Int = 0
)