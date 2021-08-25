package com.babakbelur.studiary.core.domain.model

data class Target(
    val idUser: Int = 0,
    val name: String? = null,
    val role: String? = null,
    val target: List<TargetItem> = emptyList(),
    val username: String? = null
)

data class TargetItem(
    val achieved: Int = 0,
    val g1: Int = 0,
    val course: List<Course> = emptyList(),
    val gradeTarget: Int = 0,
    val idCourse: Int = 0,
    val idTarget: Int = 0,
    val targetTime: String? = "",
    val idUser: Int = 0
)
