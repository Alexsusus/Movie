package com.example.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.FragmentMoviesList
import com.example.movie.R
import com.example.movie.SomeFragmentClickListener
import com.example.movie.model.ViewHolderMovie
import com.example.movie.model.Movie


class MoviesAdapter(
    context: Context,
    var movies: List<Movie>,
    private val selectedMovieCallback: () -> Unit
) : RecyclerView.Adapter<ViewHolderMovie>() {


    private val inflater: LayoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMovie {
        return ViewHolderMovie(inflater.inflate(R.layout.view_holder_movie, parent, false))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolderMovie, position: Int) {
        holder.bind(movies[position])
        holder.itemView.setOnClickListener {
            selectedMovieCallback.invoke()
        }
    }
}

