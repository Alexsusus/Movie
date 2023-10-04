package com.example.movie

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.adapter.ActorsAdapter
import com.example.movie.repository.ActorRepository
import com.example.movie.repository.GenreRepository
import com.example.movie.repository.MovieRepository


class FragmentMoviesDetails : Fragment() {

    private var someFragmentClickListener: SomeFragmentClickListener? = null
    private lateinit var list: RecyclerView
    private val TAG = "TAG"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)
        list = view.findViewById(R.id.recyclerView)
        val imageBackground = view.findViewById<ImageView>(R.id.image_background_poster)
        val ageLimit = view.findViewById<TextView>(R.id.age_limit)
        val title = view.findViewById<TextView>(R.id.movie_title_details)
        val genres = view.findViewById<TextView>(R.id.genres)
        val reviews = view.findViewById<TextView>(R.id.reviews)
        val storyLine = view.findViewById<TextView>(R.id.text_storyline)


        var movieId = arguments?.getInt("id_movie")
        val movieRepository = MovieRepository()
        val movie = movieId?.let { context?.let { it1 -> movieRepository.findMovieById(it1, it) } }
        if (movie != null) {
            Glide.with(imageBackground.context)
                .load(movie.background)
                .into(imageBackground)

            if (movie.isAdult) {
                ageLimit.visibility = View.VISIBLE
            } else {
                ageLimit.visibility = View.GONE
            }

            title.text = movie.title

            val genreRepository = context?.let { GenreRepository(it) }
            val genreIds = movie.genreIds ?: emptyList()
            val genreNames = genreRepository?.findGenreNamesByIds(genreIds)
            if (genreNames != null) {
                genres.text = genreNames.joinToString(", ")
            }
            reviews.text = movie.reviews.toString()
            storyLine.text = movie.storyline
        }
        val actorRepository = context?.let { ActorRepository(it) }

        if (movie != null) {
            Log.d(TAG, "onCreateView: ${movie.actors}")
        }

        val actors = movie?.actors?.let { actorRepository?.findActorsByMovieId(it) }
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        if (actors != null) {
            layoutManager.initialPrefetchItemCount = actors.size
        }
        list.layoutManager = layoutManager
        val adapter = context?.let {
            actors?.let { it1 -> ActorsAdapter(it, it1) }
        }

        list.adapter = adapter

        view.findViewById<ImageButton>(R.id.backButton).apply {
            setOnClickListener {
                someFragmentClickListener?.backToMoviesList()
            }
        }

        savedInstanceState?.let {
            val adapterState = it.getBundle("adapter_state")
            layoutManager.onRestoreInstanceState(adapterState)
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SomeFragmentClickListener) {
            someFragmentClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        someFragmentClickListener = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("adapter_state", list.layoutManager?.onSaveInstanceState())
    }

}