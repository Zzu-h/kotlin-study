package com.zzuh.moviemvvm.ui.movie_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zzuh.moviemvvm.data.repository.NetworkState
import com.zzuh.moviemvvm.databinding.ActivityMainBinding
import com.zzuh.moviemvvm.ui.viewmodel.MovieListViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MovieListViewModel
    lateinit var adapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = MovieListViewModel()
        adapter = MovieListAdapter(emptyList(), this)

        var gridLayout = GridLayoutManager(this, 3)
        binding.movieListRecyclerView.layoutManager = gridLayout
        binding.movieListRecyclerView.adapter = adapter

        setContentView(binding.root)

        viewModel.movieList.observe(this, Observer {
            adapter.updateData(it.movieList)
        })

        viewModel.networkState.observe(this, Observer {
            binding.progressBar.visibility = if (viewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else View.GONE
            binding.txtError.visibility = if (viewModel.listIsEmpty() && it == NetworkState.ERROR) View.VISIBLE else View.GONE
        })
    }
}