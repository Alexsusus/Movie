package com.example.movie.repository

import android.content.Context
import com.example.movie.model.Genre
import org.json.JSONArray
import org.json.JSONObject

class GenreRepository(private val context: Context) {

    private val genres: List<Genre> by lazy { loadGenres() }

    private fun loadGenres(): List<Genre> {
        val json = context.assets.open("genres.json").bufferedReader().use { it.readText() }
        val genresArray = JSONArray(json)

        val genresList = mutableListOf<Genre>()
        for (i in 0 until genresArray.length()) {
            val genreObject = genresArray.getJSONObject(i)
            val genre = parseGenre(genreObject)
            genresList.add(genre)
        }
        return genresList
    }

    private fun parseGenre(genreObject: JSONObject): Genre {
        val id = genreObject.getInt("id")
        val name = genreObject.getString("name")

        return Genre(id, name)
    }

    fun findGenreNamesByIds(genreIds: List<Int>): List<String> {
        val genreNames = mutableListOf<String>()

        for (genreId in genreIds) {
            val matchingGenre = genres.find { it.id == genreId }
            matchingGenre?.let {
                it.name?.let { it1 -> genreNames.add(it1) }
            }
        }

        return genreNames
    }
}