package com.example.animefacts.util

import android.annotation.SuppressLint
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@SuppressLint("NewApi")
fun formatDate(dateString: String): String {
    val dateTime = OffsetDateTime.parse(dateString)

    val formatter = DateTimeFormatter.ofPattern("d MMM, HH:mm", Locale.ENGLISH)

    return dateTime.format(formatter)
}