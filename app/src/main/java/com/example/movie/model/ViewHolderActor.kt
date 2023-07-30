package com.example.movie.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R

class ViewHolderActor(view: View): RecyclerView.ViewHolder(view) {
    private val photo = itemView.findViewById<ImageView>(R.id.pic_actor)
    private val name = itemView.findViewById<TextView>(R.id.name_actor)

    fun bind(actor: Actor) {
        photo.setImageResource(actor.photo)
        name.text = actor.name
    }
}