package com.example.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), SomeFragmentClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, FragmentMoviesList())
            .commit()



    }

    override fun selectedMovie() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, FragmentMoviesDetails())
            .commit()
    }

    override fun backToMoviesList() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, FragmentMoviesList())
            .commit()
    }

}