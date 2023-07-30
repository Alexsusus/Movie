package com.example.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.model.Actor
import com.example.movie.model.ViewHolderActor

class ActorsAdapter(
    context: Context,
    var actors: List<Actor>
) : RecyclerView.Adapter<ViewHolderActor>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderActor {
        return ViewHolderActor(inflater.inflate(R.layout.view_holder_actor,parent,false))
    }

    override fun getItemCount(): Int = actors.size

    override fun onBindViewHolder(holder: ViewHolderActor, position: Int) {
        holder.bind(actors[position])
    }
}