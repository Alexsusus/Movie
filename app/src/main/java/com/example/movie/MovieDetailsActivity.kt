package com.example.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, FragmentMoviesList())
            .commit()
    }
}