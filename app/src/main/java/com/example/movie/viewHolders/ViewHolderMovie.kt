package com.example.movie.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.model.Movie
import com.example.movie.repository.GenreRepository

class ViewHolderMovie(view: View) : RecyclerView.ViewHolder(view) {

    private val poster: ImageView = itemView.findViewById(R.id.movie_poster)
    private val ageLimit: TextView = itemView.findViewById(R.id.age_limit)
    private val genre: TextView = itemView.findViewById(R.id.genre)
    private val reviews: TextView = itemView.findViewById(R.id.reviews)
    private val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
    private val duration: TextView = itemView.findViewById(R.id.duration)
    private val star1: ImageView = itemView.findViewById(R.id.first_star)
    private val star2: ImageView = itemView.findViewById(R.id.second_star)
    private val star3: ImageView = itemView.findViewById(R.id.third_star)
    private val star4: ImageView = itemView.findViewById(R.id.fourth_star)
    private val star5: ImageView = itemView.findViewById(R.id.fifth_star)


    fun bind(movie: Movie, genreRepository: GenreRepository) {

        val genreIds = movie.genreIds ?: emptyList()
        val genreNames = genreRepository.findGenreNamesByIds(genreIds)

        Glide.with(poster.context)
            .load(movie.poster)
            .into(poster)

        if (movie.isAdult) {
            ageLimit.visibility = View.VISIBLE
        } else {
            ageLimit.visibility = View.GONE
        }

        genre.text = genreNames.joinToString(", ")
        reviews.text = movie.reviews.toString().plus(" REVIEWS")
        movieTitle.text = movie.title
        duration.text = movie.duration.toString().plus(" MIN")
        checkRating(movie.rating)
    }

    private fun checkRating(ratingDouble: Double) {
        when (ratingDouble.toInt() / 2) {
            0 -> {
                star1.setImageResource(R.drawable.star_icon_grey)
                star2.setImageResource(R.drawable.star_icon_grey)
                star3.setImageResource(R.drawable.star_icon_grey)
                star4.setImageResource(R.drawable.star_icon_grey)
                star5.setImageResource(R.drawable.star_icon_grey)
            }

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