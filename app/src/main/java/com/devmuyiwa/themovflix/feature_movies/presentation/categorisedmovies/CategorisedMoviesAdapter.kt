package com.devmuyiwa.themovflix.feature_movies.presentation.categorisedmovies

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.*
import coil.load
import com.devmuyiwa.themovflix.R
import com.devmuyiwa.themovflix.databinding.RecyclerViewCategorisedMovieItemBinding
import com.devmuyiwa.themovflix.feature_movies.presentation.categorisedmovies.model.UICategorisedMovie

class CategorisedMoviesAdapter(private val context: Context) :
    ListAdapter<UICategorisedMovie, CategorisedMoviesAdapter.ViewHolder>(ITEM_COMPARATOR) {

    inner class ViewHolder(
        private val binding: RecyclerViewCategorisedMovieItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UICategorisedMovie) {
            binding.title.text = item.title
            binding.poster.load(item.posterUrl)
            "${item.averageVote}/10 IMDb".also { binding.averageVote.text = it }
            binding.overview.text = item.overview
            val genreAdapter = MoviesGenreAdapter()
            setupRecyclerView(genreAdapter)
            genreAdapter.submitList(item.genres)
        }

        private fun setupRecyclerView(genreAdapter: MoviesGenreAdapter) {
            binding.genreRecyclerView.apply {
                adapter = genreAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,
                    false)
            }
        }

        fun navigateToDetailScreen(movieId: Long){
            binding.recyclerViewItemContainer.setOnClickListener {
                val action = CategorisedMoviesFragmentDirections
                    .actionCategorisedMoviesFragmentToMovieDetailFragment(movieId)
                it.findNavController().navigate(action)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = RecyclerViewCategorisedMovieItemBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: UICategorisedMovie = getItem(position)
        holder.bind(item)
        holder.navigateToDetailScreen(item.id)
    }

}

private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<UICategorisedMovie>() {
    override fun areItemsTheSame(
        oldItem: UICategorisedMovie,
        newItem: UICategorisedMovie,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: UICategorisedMovie,
        newItem: UICategorisedMovie,
    ): Boolean {
        return oldItem == newItem
    }
}

