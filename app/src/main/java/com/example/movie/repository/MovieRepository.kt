package com.example.movie.repository

import android.content.Context
import com.example.movie.model.Movie
import org.json.JSONArray
import org.json.JSONObject

class MovieRepository {


    fun getMovies(context: Context): List<Movie> {
        val json = context.assets.open("data.json").bufferedReader().use { it.readText() }
        val movieArray = JSONArray(json)

        val moviesList = mutableListOf<Movie>()

        for (i in 0 until movieArray.length()) {
            val movieObject = movieArray.getJSONObject(i)
            val movie = parseMovie(movieObject)
            moviesList.add(movie)
        }

        return moviesList
    }
}


private fun parseMovie(movieObject: JSONObject?): Movie {
    if (movieObject != null) {
        return Movie(
            movieObject.getString("poster_path"),
            movieObject.getBoolean("adult"),
            parseGenreIds(movieObject.getJSONArray("genre_ids")),
            movieObject.getInt("vote_count"),
            movieObject.getString("original_title"),
            movieObject.getInt("runtime"),
            movieObject.getDouble("vote_average")
        )
    } else {
        return Movie(
            "https://image.tmdb.org/t/p/w342/9HT9982bzgN5on1sLRmc1GMn6ZC.jpg",
            true,
            listOf(11),
            125,
            "Avengers: End Game",
            137,
            4.0
        )
    }
}

private fun parseGenreIds(jsonArray: JSONArray): List<Int> {
    val genreIds = mutableListOf<Int>()
    for (i in 0 until jsonArray.length()) {
        genreIds.add(jsonArray.getInt(i))
    }
    return genreIds
}