package com.babakbelur.studiary.core.domain.model

data class RangeTime(
    var rangeTime: String,
    var param: Int = 1,
    var isSelected: Boolean = false
)