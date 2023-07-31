package com.example.movie

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.adapter.MoviesAdapter
import com.example.movie.model.Movie

class FragmentMoviesList : Fragment() {

    private var someFragmentClickListener: SomeFragmentClickListener? = null
    private lateinit var list: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        list = view.findViewById(R.id.recyclerView)
        val movies = getMovies()
        val layoutManager = GridLayoutManager(context, 2)
        list.layoutManager = layoutManager

        val adapter = context?.let {
            MoviesAdapter(it, movies) {
                someFragmentClickListener?.selectedMovie()
            }
        }

        list.adapter = adapter

        savedInstanceState?.let {
            val adapterState = it.getBundle("adapter_state")
            layoutManager.onRestoreInstanceState(adapterState)
        }

        return view
    }

    companion object {
        const val TAG = "FragmentMoviesList"
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

    fun getMovies(): List<Movie> {
        return listOf(
            Movie(
                R.drawable.avengers_poster,
                13,
                "Action, Adventure, Fantasy",
                125,
                "Avengers: End Game",
                137,
                4
            ),
            Movie(
                R.drawable.tenet_poster,
                16,
                "Action, Sci-Fi, Thriller ",
                98,
                "Tenet",
                97,
                5
            ),
            Movie(
                R.drawable.black_widow_poster,
                13,
                "Action, Adventure, Sci-Fi",
                38,
                "Black Widow",
                102,
                4
            ),
            Movie(
                R.drawable.wonder_woman_1984_poster,
                13,
                "Action, Adventure, Fantasy",
                74,
                "Wonder Woman 1984",
                120,
                5
            )
        )
    }

}