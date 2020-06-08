package com.iflippie.level6_task2.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.iflippie.level6_task2.database.MovieRepository
import com.iflippie.level6_task2.model.MoviesList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val movieRepository = MovieRepository()

    val moviesList = MutableLiveData<MoviesList>()
    val error = MutableLiveData<String>()

    fun getMoviesList(year: String) {
        movieRepository.getMoviesFirstPage(year).enqueue(object : Callback<MoviesList> {
            override fun onFailure(call: Call<MoviesList>, t: Throwable) {
                error.value = t.message
            }

            override fun onResponse(call: Call<MoviesList>, response: Response<MoviesList>) {
                if (response.isSuccessful){
                    moviesList.value = response.body()
                }
                else error.value = "An error occured: ${response.errorBody().toString()}"
            }
        })
    }
}