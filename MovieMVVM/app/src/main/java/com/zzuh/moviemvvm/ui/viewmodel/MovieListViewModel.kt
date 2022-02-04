package com.zzuh.moviemvvm.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zzuh.moviemvvm.data.repository.MovieListRepository
import com.zzuh.moviemvvm.data.repository.NetworkState
import com.zzuh.moviemvvm.data.vo.MovieList

class MovieListViewModel():ViewModel() {
    private val movieListRepository = MovieListRepository()
    private var page = 1

    val movieList: LiveData<MovieList> by lazy {
        movieListRepository.fetchMovieList(page)
    }
    val networkState: LiveData<NetworkState> by lazy {
        movieListRepository.getMovieListNetworkState()
    }

    fun listIsEmpty(): Boolean = (movieList.value?.movieList?.isEmpty() == true)


    fun increasePage(): Int = ++page
    fun decreasePage(): Int = if(page == 1) page else --page
}