package com.example.movie.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.model.Actor

class ViewHolderActor(view: View): RecyclerView.ViewHolder(view) {
    private val photo = itemView.findViewById<ImageView>(R.id.pic_actor)
    private val name = itemView.findViewById<TextView>(R.id.name_actor)

    fun bind(actor: Actor) {
        Glide.with(photo.context)
           .load(actor.profile_photo)
            .into(photo)
        name.text = actor.name
    }
}