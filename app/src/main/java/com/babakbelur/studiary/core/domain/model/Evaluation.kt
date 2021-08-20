package com.babakbelur.studiary.core.domain.model

data class Evaluation(
    val evaluationItemResponse: List<EvaluationItem>,
    val idUser: Int = 0,
    val name: String? = null,
    val role: String? = null,
    val username: String? = null
)

data class EvaluationItem(
    val grade: Int = 0,
    val idCourse: Int = 0,
    val idEvaluation: Int = 0,
    val idUser: Int = 0
)
