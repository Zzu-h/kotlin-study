package com.zzuh.moviemvvm.data.repository

import androidx.lifecycle.LiveData
import com.zzuh.moviemvvm.data.datasource.MovieListNetworkDataSource
import com.zzuh.moviemvvm.data.vo.MovieList

class MovieListRepository {
    lateinit var dataSource: MovieListNetworkDataSource
    fun fetchMovieList(page: Int): LiveData<MovieList> {
        dataSource = MovieListNetworkDataSource()
        dataSource.fetchMovieList(page)

        return dataSource.downloadedMovieListResponse
    }

    fun getMovieListNetworkState(): LiveData<NetworkState> = dataSource.networkState
}