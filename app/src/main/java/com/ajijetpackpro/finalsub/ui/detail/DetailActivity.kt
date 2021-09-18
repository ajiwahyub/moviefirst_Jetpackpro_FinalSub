package com.ajijetpackpro.finalsub.ui.detail

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModelProvider
import com.ajijetpackpro.finalsub.R
import com.ajijetpackpro.finalsub.data.entity.MovieEntity
import com.ajijetpackpro.finalsub.data.entity.TvshowEntity
import com.ajijetpackpro.finalsub.databinding.ActivityDetailBinding
import com.ajijetpackpro.finalsub.utils.Helper.TYPE_MOVIE
import com.ajijetpackpro.finalsub.utils.Helper.TYPE_TVSHOW
import com.ajijetpackpro.finalsub.viewmodel.ViewModelFactory
import com.ajijetpackpro.finalsub.vo.Status
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private var titleApp = "Movie First"



    companion object {
        const  val PUT_EXTRA = "put_extra"
        const val TYPE_EXTRA = "type_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionBar(titleApp)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(
            this@DetailActivity,
            factory
        )[DetailViewModel::class.java]

        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            DetailViewModel::class.java)
        val id = intent.getIntExtra(PUT_EXTRA, 0)
        val type = intent.getStringExtra(TYPE_EXTRA)

        if (type.equals(TYPE_MOVIE, ignoreCase = true)) {
            viewModel.getDetailMovieById(id).observe(this, { movie ->
                if (movie != null){
                    when(movie.status) {
                        Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            Log.e("result", movie.data.toString())
                            binding.progressBar.visibility = View.GONE
                            movie.data?.let { showDetail(it, null) }
                        }
                        Status.ERROR -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
        else if (type.equals(TYPE_TVSHOW, ignoreCase = true)) {
            viewModel.getDetailTvShowById(id).observe(this,{ tvshow ->
                if (tvshow != null){
                    when(tvshow.status) {
                        Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            Log.e("result", tvshow.data.toString())
                            binding.progressBar.visibility = View.GONE
                            tvshow.data?.let { showDetail(null, it) }
                        }
                        Status.ERROR -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }


    }

    private fun showDetail(movie: MovieEntity?, tvshow: TvshowEntity?) {
        val uScore = movie?.uScore ?: tvshow?.uScore
        val poster = movie?.poster ?: tvshow?.poster
        val background = movie?.background ?: tvshow?.background
        val statusFav = movie?.isFavorite ?: tvshow?.isFavorite

        statusFav?.let { status ->
            setFavoriteButton(status)
        }

        binding.tvDetailTitle.text = movie?.title ?: tvshow?.title
        binding.tvDetailOverview.text = movie?.overview ?: tvshow?.overview
        binding.tvDetailRdate.text = movie?.rDate ?: tvshow?.rDate
        binding.tvDetailUserscore.text = uScore.toString()


        Glide.with(this).clear(binding.ivDetailPoster)
        Glide.with(this).load("https://image.tmdb.org/t/p/w200"+ poster).into(binding.ivDetailPoster)
        Glide.with(this).clear(binding.ivDetailBg)
        Glide.with(this).load("https://image.tmdb.org/t/p/w780"+ background).into(binding.ivDetailBg)


        binding.floatingActionButton.setOnClickListener{
            setFavoriteValue(movie, tvshow)
        }

    }

    private fun setFavoriteValue(movie: MovieEntity?, tvshow: TvshowEntity?) {
        if (movie != null) {
                setFavToast(movie.isFavorite, movie.title)
                detailViewModel.setFavoriteMovie(movie)

        } else {
            if (tvshow != null) {
                setFavToast(tvshow.isFavorite, tvshow.title)
                detailViewModel.setFavoriteTvshow(tvshow)
            }
        }
    }

    private fun setFavToast(state: Boolean, title: String?){
        if (state) {
            Toast.makeText(this, getString(R.string.unvaforite_title, title), Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, getString(R.string.vaforite_title, title), Toast.LENGTH_LONG).show()
        }
    }

    private fun setFavoriteButton(status: Boolean) {
        if(status) {
            binding.floatingActionButton.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else binding.floatingActionButton.setImageResource(R.drawable.ic_outline_favorite_border_24)

    }

    private fun setActionBar(titleApp: String) {
        val icon = ResourcesCompat.getDrawable(resources, R.drawable.ic_tmdb, null)?.toBitmap()
        val fixedIcon = BitmapDrawable(resources,
            icon.let { Bitmap.createScaledBitmap(it!!, 60, 60, true) })
        supportActionBar?.setHomeAsUpIndicator(fixedIcon)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = titleApp
        }
    }

}