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
    val displayName: String,
    val apiName: String
) {
    ALL(null, "All Ratings", "G - All Ages"),
    G("g", "All Ages", "G - All Ages"),
    PG("pg", "Children", "PG - Children"),
    PG_13("pg13", "Teens 13+", "PG-13 - Teens 13 or older"),
    R17("r17", "17+ (violence & profanity)", "R - 17+ (violence & profanity)"),
    R_PLUS("r", "Mild Nudity", "R+ - Mild Nudity"),
//    RX("rx", "Hentai", "Rx - Hentai")
}

