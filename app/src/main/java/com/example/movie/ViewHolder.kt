package com.example.movie

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.model.Movie

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val poster: ImageView = itemView.findViewById(R.id.movie_poster)
    private val ageLimit: TextView = itemView.findViewById(R.id.age_limit)
    private val genre: TextView = itemView.findViewById(R.id.genre)
    private val reviews: TextView = itemView.findViewById(R.id.count_reviews)
    private val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
    private val duration: TextView = itemView.findViewById(R.id.count_duration)
    private val star1: ImageView = itemView.findViewById(R.id.first_star)
    private val star2: ImageView = itemView.findViewById(R.id.second_star)
    private val star3: ImageView = itemView.findViewById(R.id.third_star)
    private val star4: ImageView = itemView.findViewById(R.id.fourth_star)
    private val star5: ImageView = itemView.findViewById(R.id.fifth_star)


    fun bind(movie: Movie) {
        poster.setImageResource(movie.poster)
        ageLimit.text = movie.ageLimit
        genre.text = movie.genre
        reviews.text = movie.reviews.toString()
        movieTitle.text = movie.movieTitle
        duration.text = movie.duration.toString()
        checkRating(movie.rating)
    }

    private fun checkRating(rating: Int) {
        when (rating) {
            1 -> {
                star2.setImageResource(R.drawable.star_icon_grey)
                star3.setImageResource(R.drawable.star_icon_grey)
                star4.setImageResource(R.drawable.star_icon_grey)
                star5.setImageResource(R.drawable.star_icon_grey)
            }

            2 -> {
                star3.setImageResource(R.drawable.star_icon_grey)
                star4.setImageResource(R.drawable.star_icon_grey)
                star5.setImageResource(R.drawable.star_icon_grey)
            }

            3 -> {
                star4.setImageResource(R.drawable.star_icon_grey)
                star5.setImageResource(R.drawable.star_icon_grey)
            }

            4 -> star5.setImageResource(R.drawable.star_icon_grey)
            5 -> println("$movieTitle has 5 stars")
            else -> println("Wrong Rating")
        }
    }
}