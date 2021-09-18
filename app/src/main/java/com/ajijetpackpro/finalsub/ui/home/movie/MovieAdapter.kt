package com.ajijetpackpro.finalsub.ui.home.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ajijetpackpro.finalsub.data.entity.MovieEntity
import com.ajijetpackpro.finalsub.databinding.ItemRowMovieBinding
import com.ajijetpackpro.finalsub.ui.detail.DetailActivity
import com.ajijetpackpro.finalsub.utils.Helper
import com.ajijetpackpro.finalsub.utils.loadFromUrl


class MovieAdapter: PagedListAdapter<MovieEntity, MovieAdapter.ListViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }


    inner class ListViewHolder(private val binding: ItemRowMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {

                tvItemTitle.text = movie.title
                tvItemUserscore.text = movie.uScore.toString()
                tvItemRdate.text = movie.rDate

                movie.poster?.let {
                    ivItemPoster.loadFromUrl("https://image.tmdb.org/t/p/w200/$it")
                }

                movie.background?.let {
                    ivItemBg.loadFromUrl("https://image.tmdb.org/t/p/w780/$it")
                }

                itemView.setOnClickListener {
                    val move = Intent(itemView.context, DetailActivity::class.java)
                    move.putExtra(DetailActivity.PUT_EXTRA, movie.id)
                    move.putExtra(DetailActivity.TYPE_EXTRA, Helper.TYPE_MOVIE)
                    itemView.context.startActivity(move)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemsMovieBinding =
            ItemRowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

}