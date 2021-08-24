package com.babakbelur.studiary.core.domain.model

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
