package com.example.movie.model



data class Movie(
    val posterUrl: String,
    val ageLimit: Boolean,
    val genre: List<Int>,
    val reviews: Int,
    val movieTitle: String,
    val duration: Int,
    val rating: Double
)

