package com.iflippie.level6_task2.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.iflippie.level6_task2.model.MovieItem
import com.iflippie.level6_task2.R
import com.iflippie.level6_task2.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private var movieList = arrayListOf<MovieItem>()
    private var movieAdapter = MovieAdapter(movieList) { movieItem -> onMovieClick(movieItem) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initViewModel()
    }

    private fun initViews() {
        rvMovies.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        rvMovies.adapter = movieAdapter
        btnSend.setOnClickListener {
            val yearInput = etYear.text.toString()
            if (yearInput.isBlank() ||  yearInput.toInt() < 1875) {
                Toast.makeText(this, "Please enter a valid year", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.getMoviesList(yearInput)
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.moviesList.observe(this, Observer { it -> movieList.clear()
            it.results.forEach {
                movieList.add(it)
            }
            movieAdapter.notifyDataSetChanged()
        })
        viewModel.error.observe(this, Observer {
            Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun onMovieClick(movieItem: MovieItem) {
        val detailIntent = Intent(this, DetailActivity::class.java)
        detailIntent.putExtra(INTENT_EXTRA_MOVIE, movieItem)
        startActivity(detailIntent)
    }

    companion object {
        const val INTENT_EXTRA_MOVIE = "INTENT_EXTRA_MOVIE"
    }
}
