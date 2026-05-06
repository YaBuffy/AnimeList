package com.example.animefacts.domain.model

import androidx.annotation.StringRes
import com.example.animefacts.R

enum class ViewingStatus(@StringRes val titleRes: Int) {
    NOT_WATCHED(R.string.not_watched),
    WATCHING(R.string.watching),
    COMPLETED(R.string.completed),
    ON_HOLD(R.string.on_hold),
    DROPPED(R.string.dropped),
    PLAN_TO_WATCH(R.string.plan_to_watch);

}