package com.babakbelur.studiary.core.domain.model

import com.babakbelur.studiary.core.data.remote.response.factor.FactorResponse

data class Factor(
    val factorResponse: List<FactorItem>,
    val idUser: Int = 0,
    val name: String? = null,
    val role: String? = null,
    val username: String? = null
)

data class FactorItem(
    val extraPaidCourse: String,
    val extracurricular: String,
    val freeTime: Int,
    val g1: Int,
    val health: String,
    val idFactor: Int,
    val idUser: Int,
    val studyTime: Int,
    val takeHigherEducation: String
)
