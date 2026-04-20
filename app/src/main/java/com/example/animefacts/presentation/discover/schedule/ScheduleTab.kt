package com.example.animefacts.presentation.discover.schedule

import androidx.annotation.StringRes
import com.example.animefacts.R

enum class ScheduleTab(
    @StringRes val titleRes: Int,
    val apiValue: String
) {
    Monday(R.string.monday, "monday"),
    Tuesday(R.string.tuesday, "tuesday"),
    Wednesday(R.string.wednesday, "wednesday"),
    Thursday(R.string.thursday, "thursday"),
    Friday(R.string.friday, "friday"),
    Saturday(R.string.saturday, "saturday"),
    Sunday(R.string.sunday, "sunday")
}