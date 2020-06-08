package com.iflippie.level6_task2.database

import com.iflippie.level6_task2.model.MoviesList
import retrofit2.Call

class MovieRepository {
    private val movieApi: MovieApiService = MovieApi.createApi()
    fun getMoviesFirstPage(year: String): Call<MoviesList> {
        return movieApi.getMoviesFirstPage(year)
    }
}