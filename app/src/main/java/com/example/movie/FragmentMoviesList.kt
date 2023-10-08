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
import com.example.movie.repository.GenreRepository
import com.example.movie.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentMoviesList : Fragment() {

    private var someFragmentClickListener: SomeFragmentClickListener? = null
    private lateinit var list: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        list = view.findViewById(R.id.recyclerView)

        val movieRepository = MovieRepository()
        val genreRepository = context?.let { GenreRepository(it) }

        val uiScope = CoroutineScope(Dispatchers.IO + Job())
        uiScope.launch {

            try {
                val movies =
                    movieRepository.getMovies(context ?: throw Exception("Context is null"))

                withContext(Dispatchers.Main) {
                    val layoutManager = GridLayoutManager(context, 2)
                    list.layoutManager = layoutManager

                    val adapter = genreRepository?.let {
                        MoviesAdapter(
                            requireContext(), movies,
                            it
                        )
                    }
                    list.adapter = adapter


                    savedInstanceState?.let {
                        val adapterState = it.getBundle("adapter_state")
                        layoutManager.onRestoreInstanceState(adapterState)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
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

}