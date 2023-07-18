package com.example.movie

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

class FragmentMoviesList : Fragment() {

    private var someFragmentClickListener: SomeFragmentClickListener? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        view?.findViewById<FrameLayout>(R.id.frameLayoutMovie)?.apply {
            setOnClickListener{
                someFragmentClickListener?.selectedMovie()
            }
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is SomeFragmentClickListener){
            someFragmentClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        someFragmentClickListener = null
    }

}