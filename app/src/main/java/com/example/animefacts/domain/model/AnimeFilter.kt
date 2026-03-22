package com.example.animefacts.domain.model

enum class AnimeType(
    val apiValue: String?,
    val displayName: String
){
    ALL(null, "All Types"),
    TV("tv", "TV"),
    MOVIE("movie", "Movie"),
    SPECIAL("special", "Special"),
    OVA("ova", "OVA"),
    ONA("ona", "ONA"),
    MUSIC("music", "Music"),
    CM("cm", "Co-produced"),
    PM("pm", "Producers' Animation")
}
enum class AnimeStatus(
    val apiValue: String?,
    val displayName: String
){
    ALL(null, "All Status"),
    AIRING("airing", "Currently Airing"),
    UPCOMING("upcoming", "Upcoming"),
    COMPLETE("complete", "Completed"),
}
enum class AnimeRating(
    val apiValue: String?,
    val displayName: String
) {
    ALL(null, "All Ratings"),
    G("g", "All Ages"),
    PG("pg", "Children"),
    PG_13("pg13", "Teens 13+"),
    R17("r17", "17+ (violence & profanity)"),
    R_PLUS("r", "Mild Nudity"),
    RX("rx", "Hentai")
}

