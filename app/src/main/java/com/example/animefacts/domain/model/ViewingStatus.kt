package com.example.animefacts.domain.model

enum class ViewingStatus(val status: String) {
    NOT_WATCHED("Not Watched"),
    WATCHING("Watching"),
    COMPLETED("Completed"),
    ON_HOLD("On-Hold"),
    DROPPED("Dropped"),
    PLAN_TO_WATCH("Planned");

}