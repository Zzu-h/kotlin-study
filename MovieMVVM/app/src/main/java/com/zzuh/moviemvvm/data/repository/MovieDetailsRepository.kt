package com.zzuh.moviemvvm.data.repository

import androidx.lifecycle.LiveData
import com.zzuh.moviemvvm.data.datasource.MovieDetailsNetworkDataSource
import com.zzuh.moviemvvm.data.vo.MovieDetails

class MovieDetailsRepository {
    lateinit var dataSource: MovieDetailsNetworkDataSource

    fun fetchMovieDetails(movieId:Int): LiveData<MovieDetails>{
        dataSource = MovieDetailsNetworkDataSource()
        dataSource.fetchMovieDetails(movieId)

        return dataSource.downloadedMovieDetailsResponse
    }

    fun getMovieDtetailsNetworkState(): LiveData<NetworkState> = dataSource.networkState
}