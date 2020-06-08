package com.iflippie.level6_task2.database

import com.iflippie.level6_task2.model.MoviesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApiService {
    @GET("3/discover/movie?api_key=3605ea0def25990555e72cdad3f5baa9&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    fun getMoviesFirstPage(@Query("year") year: String): Call<MoviesList>
}