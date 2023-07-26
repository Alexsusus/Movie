package com.example.movie

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.model.Movie

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val poster: ImageView = itemView.findViewById(R.id.movie_poster)
    private val ageLimit: TextView = itemView.findViewById(R.id.age_limit)
    private val genre: TextView = itemView.findViewById(R.id.genre)
    private val reviews: TextView = itemView.findViewById(R.id.count_reviews)
    private val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
    private val duration: TextView = itemView.findViewById(R.id.count_duration)



    fun bind(movie: Movie) {
        poster.setImageResource(movie.poster)
        ageLimit.text = movie.ageLimit.toString()
        genre.text = movie.genre
        reviews.text = movie.reviews.toString()
        movieTitle.text = movie.movieTitle
        duration.text = movie.duration.toString()
    }
}