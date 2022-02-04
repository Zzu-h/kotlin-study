package com.zzuh.moviemvvm.ui.single_movie_details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.zzuh.moviemvvm.data.api.POSTER_BASE_URL
import com.zzuh.moviemvvm.data.repository.NetworkState
import com.zzuh.moviemvvm.data.vo.MovieDetails
import com.zzuh.moviemvvm.databinding.ActivitySingleMovieBinding
import com.zzuh.moviemvvm.ui.viewmodel.MovieDetailsViewModel
import java.text.NumberFormat
import java.util.*

class SingleMovie : AppCompatActivity() {
    lateinit var binding: ActivitySingleMovieBinding
    private lateinit var viewModel: MovieDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val movieId: Int = intent.getIntExtra("id", 1)

        viewModel = MovieDetailsViewModel(movieId)
        viewModel.movieDetails.observe(this, Observer {
            bindUI(it)
        })
        viewModel.networkState.observe(this, Observer {
            binding.progressBar.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            binding.txtError.visibility = if (it == NetworkState.ERROR) View.VISIBLE else View.GONE
        })
    }

    fun bindUI( it: MovieDetails){
        binding.movieTitle.text = it.title
        binding.movieTagline.text = it.tagline
        binding.movieReleaseDate.text = it.releaseDate
        binding.movieRating.text = it.rating.toString()
        binding.movieRuntime.text = it.runtime.toString() + " minutes"
        binding.movieOverview.text = it.overview

        val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
        binding.movieBudget.text = formatCurrency.format(it.budget)
        binding.movieRevenue.text = formatCurrency.format(it.revenue)

        val moviePosterURL = POSTER_BASE_URL + it.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(binding.ivMoviePoster);
    }
}