package com.babakbelur.studiary.core.utils

import com.babakbelur.studiary.core.domain.model.RangeTime

object Constants {

    const val REQUEST_TIMEOUT = 10L

    const val BASE_URL = ""

    val STUDY_TIME_VALUE = listOf(
        RangeTime(
            "<2 JAM",
            1
        ),
        RangeTime(
            "2-5 JAM",
            2
        ),
        RangeTime(
            "5-10 JAM",
            3
        ),
        RangeTime(
            ">10 JAM",
            4
        ),
    )

    val FREE_TIME_VALUE = listOf(
        RangeTime(
            "<2 JAM",
            1
        ),
        RangeTime(
            "2-5 JAM",
            2
        ),
        RangeTime(
            "5-8 JAM",
            3
        ),
        RangeTime(
            "8-15 JAM",
            4
        ),
        RangeTime(
            ">15 JAM",
            5
        ),
    )
}