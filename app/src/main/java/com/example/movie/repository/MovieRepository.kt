package com.example.movie.repository

import android.content.Context
import android.util.Log
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
            if (movie != null) {
                moviesList.add(movie)
            }
        }

        return moviesList
    }
}


private fun parseMovie(movieObject: JSONObject?): Movie? {
    return movieObject?.optString("original_language", "")?.let {
        Movie(
            popularity = movieObject.optDouble("popularity") ?: 0.0,
            reviews = movieObject.optInt("vote_count") ?: 0,
            isVideo = movieObject.optBoolean("video") ?: false,
            poster = movieObject.optString("poster_path"),
            id = movieObject.optInt("id") ?: -1,
            isAdult = movieObject.optBoolean("adult") ?: false,
            background = movieObject.optString("backdrop_path"),
            originalLanguage = it,
            originalTitle = movieObject.optString("original_title", ""),
            duration = movieObject.optInt("runtime") ?: 0,
            genreIds = movieObject.optJSONArray("genre_ids")?.let { it1 -> parseGenreByIds(it1) },
            title = movieObject.optString("title", ""),
            actors = movieObject.optJSONArray("actors")?.let { it1 -> parseActorsForMovie(it1) },
            rating = movieObject.optDouble("vote_average") ?: 0.0,
            storyline = movieObject.optString("overview", ""),
            releaseDate = movieObject.optString("release_date", "")
        )
    }
}

private fun parseActorsForMovie(actorsArray: JSONArray?): List<Int>? {
    val actors = mutableListOf<Int>()
    if (actorsArray != null) {
        for (i in 0 until actorsArray.length()) {
            val actorId = actorsArray.optInt(i)
            actors.add(actorId)
            Log.d("parseActorsForMovie", actors.size.toString())
        }
    }
    return actors.takeIf { it.isNotEmpty() }
}

private fun parseGenreByIds(genresIdsArray: JSONArray): MutableList<Int>? {
    val genreIds = mutableListOf<Int>()
    for (i in 0 until genresIdsArray.length()) {
        genreIds.add(genresIdsArray.getInt(i))
    }
    return genreIds.takeIf { it.isNotEmpty() }


}