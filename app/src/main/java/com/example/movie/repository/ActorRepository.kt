package com.example.movie.repository

import android.content.Context
import android.util.Log
import com.example.movie.model.Actor
import org.json.JSONArray
import org.json.JSONObject

class ActorRepository {

    fun getActors(context: Context): List<Actor> {

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

    private fun parseActor(actorObject: JSONObject):Actor{
        val gender = actorObject.getInt("gender")
        val id = actorObject.getInt("id")
        val name = actorObject.getString("name")
        val originalName = actorObject.getString("original_name")
        val profilePath = actorObject.optString("profile_path")
        Log.d("ActorParsing", "Gender: $gender, ID: $id, Name: $name, Original Name: $originalName, Profile Path: $profilePath")
        return Actor(gender, id, name, originalName, profilePath)
    }
}