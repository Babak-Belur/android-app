package com.babakbelur.studiary.presentation.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.ddMMMMyFormat(): String {
    val outputFormat = SimpleDateFormat("dd MMMM y", Locale.getDefault())
    return outputFormat.format(this)
}

fun Date.ddMMMMyyyyFormat(): String {
    val outputFormat = SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault())
    return outputFormat.format(this)
}

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun String.ddMMMMyFormat(): String {
    return if (this.isEmpty()) this else {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM y", Locale.getDefault())
        val date = inputFormat.parse(this)
        outputFormat.format(date)
    }
}

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun String.ddMMMMyyyyFormat(): String {
    return if (this.isEmpty()) this else {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault())
        val date = inputFormat.parse(this)
        outputFormat.format(date)
    }
}