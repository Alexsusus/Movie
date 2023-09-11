package com.example.movie.repository

import android.content.Context
import com.example.movie.model.Actor
import org.json.JSONArray
import org.json.JSONObject

class ActorRepository(private val context: Context) {

    private val actors: List<Actor> by lazy { loadActors() }
    fun loadActors(): List<Actor> {

        val json = context.assets.open("people.json").bufferedReader().use { it.readText() }
        val actorsArray = JSONArray(json)

        val actors = mutableListOf<Actor>()
        for (i in 0 until actorsArray.length()) {
            val actorObject = actorsArray.getJSONObject(i)
            val actor = parseActor(actorObject)
            actors.add(actor)
        }
        return actors
    }

    private fun parseActor(actorObject: JSONObject): Actor {
        val gender = actorObject.getInt("gender")
        val id = actorObject.getInt("id")
        val name = actorObject.getString("name")
        val originalName = actorObject.getString("original_name")
        val profilePath = actorObject.optString("profile_path")
        return Actor(gender, id, name, originalName, profilePath)
    }

    fun findActorsByMovieId(actorsIdsInMovie: List<Int>): List<Actor> {
        val actorsFound = mutableListOf<Actor>()

        for (actorId in actorsIdsInMovie) {
            val matchingActor = actors.find { it.id == actorId }
            if (matchingActor != null) {
                actorsFound.add(matchingActor)
            }
        }
        return actors
    }
}