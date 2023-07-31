package com.example.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), SomeFragmentClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, FragmentMoviesList())
                .commit()
            if (supportFragmentManager.findFragmentByTag(FragmentMoviesList.TAG) == null) {
                supportFragmentManager.beginTransaction()
                    .add(R.id.main_container, FragmentMoviesList(), FragmentMoviesList.TAG)
                    .commit()
            }
        }
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