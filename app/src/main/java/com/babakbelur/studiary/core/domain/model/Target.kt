package com.babakbelur.studiary.core.domain.model

import com.babakbelur.studiary.core.data.remote.response.target.TargetResponse

data class Target(
    val idUser: Int = 0,
    val name: String? = null,
    val role: String? = null,
    val targetResponse: List<TargetItem>,
    val username: String? = null
)

data class TargetItem(
    val achieved: Boolean = false,
    val grade: Int = 0,
    val idCourse: Int,
    val idTarget: Int,
    val targetTime: Int
)
