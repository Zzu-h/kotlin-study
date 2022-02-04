package com.zzuh.moviemvvm.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zzuh.moviemvvm.data.repository.MovieDetailsRepository
import com.zzuh.moviemvvm.data.repository.NetworkState
import com.zzuh.moviemvvm.data.vo.MovieDetails

class MovieDetailsViewModel(movieId: Int):ViewModel() {
    private val movieDetailsRepository = MovieDetailsRepository()

    val movieDetails : LiveData<MovieDetails> by lazy {
        movieDetailsRepository.fetchMovieDetails(movieId)
    }

    val networkState : LiveData<NetworkState> by lazy {
        movieDetailsRepository.getMovieDtetailsNetworkState()
    }
}