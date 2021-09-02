package com.babakbelur.studiary.core.domain.model

data class ResultModel<T>(
    val data: T
)

data class ResultListModel<T>(
    val data: List<T>
)