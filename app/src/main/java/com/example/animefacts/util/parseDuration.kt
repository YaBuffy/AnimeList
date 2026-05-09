package com.example.animefacts.util

import android.util.Log

fun parseDuration(durationStr: String?): Int {
    if (durationStr == null) return 0

    val numbers = Regex("\\d+").findAll(durationStr).map { it.value.toInt() }.toList()
    Log.d("stats", "parseDuration numbers: $numbers")
    return when {
        // "1 hr 30 min" -> numbers[0]*60 + numbers[1]
        durationStr.contains("hr") && numbers.size >= 2 -> numbers[0] * 60 + numbers[1]
        // "1 hr" -> numbers[0]*60
        durationStr.contains("hr") && numbers.isNotEmpty() -> numbers[0] * 60
        // "24 min" -> numbers[0]
        numbers.isNotEmpty() -> numbers[0]
        else -> 0
    }
}