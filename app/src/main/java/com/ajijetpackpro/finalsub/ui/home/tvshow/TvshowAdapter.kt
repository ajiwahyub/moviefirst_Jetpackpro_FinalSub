package com.ajijetpackpro.finalsub.ui.home.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ajijetpackpro.finalsub.data.entity.TvshowEntity
import com.ajijetpackpro.finalsub.databinding.ItemRowMovieBinding
import com.ajijetpackpro.finalsub.ui.detail.DetailActivity
import com.ajijetpackpro.finalsub.utils.Helper
import com.ajijetpackpro.finalsub.utils.loadFromUrl

class TvshowAdapter: PagedListAdapter<TvshowEntity, TvshowAdapter.ListViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvshowEntity>() {
            override fun areItemsTheSame(oldItem: TvshowEntity, newItem: TvshowEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: TvshowEntity, newItem: TvshowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ListViewHolder(private val binding: ItemRowMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow: TvshowEntity) {
            with(binding) {
                tvItemTitle.text = tvshow.title
                tvItemUserscore.text = tvshow.uScore.toString()
                tvItemRdate.text = tvshow.rDate

                tvshow.poster?.let {
                    ivItemPoster.loadFromUrl("https://image.tmdb.org/t/p/w185/$it")
                }

                tvshow.background?.let {
                    ivItemBg.loadFromUrl("https://image.tmdb.org/t/p/w780/$it")
                }
                itemView.setOnClickListener {
                    val move = Intent(itemView.context, DetailActivity::class.java)
                    move.putExtra(DetailActivity.PUT_EXTRA, tvshow.id)
                    move.putExtra(DetailActivity.TYPE_EXTRA, Helper.TYPE_TVSHOW)
                    itemView.context.startActivity(move)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvshowAdapter.ListViewHolder {
        val itemsTvshowBinding =
            ItemRowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemsTvshowBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val tvshow = getItem(position)
        if (tvshow != null) {
            holder.bind(tvshow)
        }
    }
}