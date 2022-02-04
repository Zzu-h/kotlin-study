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
import com.zzuh.moviemvvm.data.vo.MovieList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MovieListNetworkDataSource {

    private val _networkState = MutableLiveData<NetworkState>()
    private val _downloadedMovieListResponse = MutableLiveData<MovieList>()

    val networkState: LiveData<NetworkState> get() = _networkState
    val downloadedMovieListResponse: LiveData<MovieList> get() = _downloadedMovieListResponse

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api = retrofit.create(TheMovieDBInterface::class.java)

    fun fetchMovieList(page: Int){
        val callGetSearch = api.getMovieList("7070509", page, API_KEY)
        var result: MovieList
        _networkState.postValue(NetworkState.LOADING)

        callGetSearch.enqueue(object :Callback<MovieList>{
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                Log.d("NetworkDataSource",response.raw().toString())
                result = response.body() as MovieList
                _downloadedMovieListResponse.postValue(result)
                _networkState.postValue(NetworkState.LOADED)
            }

            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                _networkState.postValue(NetworkState.ERROR)
                t.printStackTrace()
            }
        })
    }
}