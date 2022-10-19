package com.devmuyiwa.themovflix.feature_movies.presentation.categorisedmovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.devmuyiwa.themovflix.databinding.RecyclerViewMovieGenreItemBinding

class MoviesGenreAdapter : ListAdapter<String, MoviesGenreAdapter.ViewHolder>(ITEM_COMPARATOR) {

    inner class ViewHolder(
        private val binding: RecyclerViewMovieGenreItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.genre.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = RecyclerViewMovieGenreItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: String = getItem(position)
        holder.bind(item)
    }
}

private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}

