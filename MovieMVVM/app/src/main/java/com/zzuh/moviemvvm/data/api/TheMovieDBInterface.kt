package com.zzuh.moviemvvm.data.api

import com.zzuh.moviemvvm.data.vo.MovieDetails
import com.zzuh.moviemvvm.data.vo.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY = "28067da966b4b33cf2e89be0850b658a"
const val BASE_URL = "https://api.themoviedb.org/"

const val LAN_CODE = "ko-KR"
const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"

interface TheMovieDBInterface {

    @GET("4/list/{list_id}")
    fun getMovieList(
        @Path("list_id") id : String,
        @Query("page") page: Int,
        @Query("api_key") api_key: String,
    ): Call<MovieList>

    @GET("3/movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") id : Int,
        @Query("api_key") api_key: String,
        @Query("language") lan: String,
    ): Call<MovieDetails>

}