package com.babakbelur.studiary.core.domain.model

data class Predict(
    val freeTime: String? = null,
    val preTestScore: Int = 0,
    val grade: Int = 0,
    val predictedScore: Double = 0.0,
    val studyTime: String? = null
)
