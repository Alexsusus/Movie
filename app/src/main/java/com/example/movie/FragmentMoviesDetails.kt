package com.example.movie

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.adapter.ActorsAdapter
import com.example.movie.adapter.MoviesAdapter
import com.example.movie.model.Actor


class FragmentMoviesDetails : Fragment() {

    private var someFragmentClickListener: SomeFragmentClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)
        val list = view.findViewById<RecyclerView>(R.id.recyclerView)
        val actors = getActors()
        val adapter = context?.let { ActorsAdapter(it, actors) }
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        list.layoutManager = GridLayoutManager(context, 4)

        view.findViewById<ImageButton>(R.id.backButton).apply {
            setOnClickListener {
                someFragmentClickListener?.backToMoviesList()
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

    private fun getActors(): List<Actor> {
        return listOf(
            Actor(
                R.drawable.pic_robert,
                "R.string.nameRobertDJ"
            ),
            Actor(
                R.drawable.pic_chris_evans,
                "Chris Evans"
            ),
            Actor(
                R.drawable.pic_mark,
                "Mark Ruffalo"
            ),
            Actor(
                R.drawable.pic_chris_h,
                "Chris Hemsworth"
            )
        )
    }
}