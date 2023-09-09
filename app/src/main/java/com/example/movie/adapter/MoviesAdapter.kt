package com.example.movie.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.FragmentMoviesDetails
import com.example.movie.R
import com.example.movie.viewHolders.ViewHolderMovie
import com.example.movie.model.Movie
import com.example.movie.repository.GenreRepository


class MoviesAdapter(
    context: Context,
    var movies: List<Movie>,
    private val genreRepository: GenreRepository
) : RecyclerView.Adapter<ViewHolderMovie>() {


    private val inflater: LayoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMovie {
        return ViewHolderMovie(inflater.inflate(R.layout.view_holder_movie, parent, false))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolderMovie, position: Int) {

        holder.bind(movies[position], genreRepository)
        holder.itemView.setOnClickListener {

            //put extra (id movie)
            val fragmentMoviesDetails = FragmentMoviesDetails()
            val args = Bundle()
            args.putInt("id_movie", movies[position].id)
            fragmentMoviesDetails.arguments = args

            //to fragment movies details
            val fragmentManager = (holder.itemView.context as FragmentActivity).supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.main_container, fragmentMoviesDetails)
                .commit()

        }
    }
}

