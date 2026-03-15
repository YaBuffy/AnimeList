package com.example.animefacts.presenter.home

import androidx.annotation.StringRes
import com.example.animefacts.R

enum class HomeTab(
    @StringRes val titleRes: Int  // Храним ID ресурса, а не строку
) {
    Ongoing(R.string.ongoing),
    Completed(R.string.completed),
    Upcoming(R.string.upcoming),
    Movie(R.string.movie)
}