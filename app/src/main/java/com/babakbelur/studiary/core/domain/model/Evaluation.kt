package com.babakbelur.studiary.core.domain.model

data class Evaluation(
    val evaluation: List<EvaluationItem>,
    val idUser: Int = 0,
    val name: String? = null,
    val role: String? = null,
    val username: String? = null
)

data class EvaluationItem(
    val date: String? = "",
    val freeTime: String? = "",
    val studyTime: String? = "",
    val grade: Int = 0,
    val idEvaluation: Int = 0,
    val target: List<TargetItem> = emptyList()
)
