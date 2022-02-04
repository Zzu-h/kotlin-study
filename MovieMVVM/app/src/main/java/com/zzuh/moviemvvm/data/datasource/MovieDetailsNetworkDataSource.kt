package com.zzuh.moviemvvm.data.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zzuh.moviemvvm.data.api.API_KEY
import com.zzuh.moviemvvm.data.api.BASE_URL
import com.zzuh.moviemvvm.data.api.LAN_CODE
import com.zzuh.moviemvvm.data.api.TheMovieDBInterface
import com.zzuh.moviemvvm.data.repository.NetworkState
import com.zzuh.moviemvvm.data.vo.MovieDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MovieDetailsNetworkDataSource {

    private val _networkState = MutableLiveData<NetworkState>()
    private val _downloadedMovieDetailsResponse = MutableLiveData<MovieDetails>()

    val networkState: LiveData<NetworkState> get() = _networkState
    val downloadedMovieDetailsResponse: LiveData<MovieDetails> get() = _downloadedMovieDetailsResponse

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api = retrofit.create(TheMovieDBInterface::class.java)

    fun fetchMovieDetails(movieId: Int){
        val callGetSearch = api.getMovieDetails(movieId, API_KEY, LAN_CODE)
        var result: MovieDetails
        _networkState.postValue(NetworkState.LOADING)

        callGetSearch.enqueue(object :Callback<MovieDetails>{
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                Log.d("NetworkDataSource",response.raw().toString())
                result = response.body() as MovieDetails
                _downloadedMovieDetailsResponse.postValue(result)
                _networkState.postValue(NetworkState.LOADED)
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                _networkState.postValue(NetworkState.ERROR)
                t.printStackTrace()
            }
        })
    }
}