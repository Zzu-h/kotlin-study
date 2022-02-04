package com.zzuh.moviemvvm.ui.movie_list

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zzuh.moviemvvm.data.api.POSTER_BASE_URL
import com.zzuh.moviemvvm.data.vo.Movie
import com.zzuh.moviemvvm.databinding.MovieListItemBinding
import com.zzuh.moviemvvm.ui.single_movie_details.SingleMovie

class MovieListViewHolder(val binding: MovieListItemBinding): RecyclerView.ViewHolder(binding.root)

class MovieListAdapter(private var movie: List<Movie>, val context: Context): RecyclerView.Adapter<MovieListViewHolder>() {
    override fun getItemCount(): Int = movie.size

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val binding = holder.binding
        var movie = this.movie[position]
        binding.cvMovieTitle.text = movie?.title
        binding.cvMovieReleaseDate.text =  movie?.releaseDate

        val moviePosterURL = POSTER_BASE_URL + movie?.posterPath
        Glide.with(context)
            .load(moviePosterURL)
            .into(binding.cvIvMoviePoster);

        binding.cardView.isClickable = true
        binding.cardView.setOnClickListener{
            val intent = Intent(context, SingleMovie::class.java)
            intent.putExtra("id", movie?.id)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder
        = MovieListViewHolder(MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    fun updateData(newData: List<Movie>){
        this.movie = newData
        notifyDataSetChanged()
    }
}