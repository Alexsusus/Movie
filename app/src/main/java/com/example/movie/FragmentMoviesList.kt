package com.example.movie

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.adapter.MoviesAdapter
import com.example.movie.model.Movie

class FragmentMoviesList : Fragment() {

    private var someFragmentClickListener: SomeFragmentClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        val list = view.findViewById<RecyclerView>(R.id.recyclerView)
        val movies = getMovies()
        val adapter = context?.let { MoviesAdapter(it, movies) }
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false )
        list.layoutManager = GridLayoutManager(context,2)

        view?.findViewById<FrameLayout>(R.id.frameLayoutMovie)?.apply {
            setOnClickListener {
                someFragmentClickListener?.selectedMovie()
            }

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

    fun getMovies(): List<Movie> {
        return listOf(
            Movie(
                "C:\\Users\\Alex\\AndroidStudioProjects\\MayComeBack\\Movie\\app\\src\\main\\res\\drawable\\poster_avengers.webp",
                13,
                "genre",
                "1 reviews",
                "title",
                "dur 1"
            ),
            Movie(
                "C:\\Users\\Alex\\AndroidStudioProjects\\MayComeBack\\Movie\\app\\src\\main\\res\\drawable\\pic_chris_h.webp",
                99,
                "genre2",
                "21 reviews",
                "2title",
                "2dur 1"
            ),
            Movie(
                "C:\\Users\\Alex\\AndroidStudioProjects\\MayComeBack\\Movie\\app\\src\\main\\res\\drawable\\pic_chris_h.webp",
                999,
                "genre23",
                "213 reviews",
                "23title",
                "23dur 1"
            )
        )
    }

}