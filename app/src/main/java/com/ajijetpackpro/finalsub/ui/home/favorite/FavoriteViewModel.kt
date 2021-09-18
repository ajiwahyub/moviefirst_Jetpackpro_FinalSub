package com.ajijetpackpro.finalsub.ui.home.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ajijetpackpro.finalsub.data.entity.MovieEntity
import com.ajijetpackpro.finalsub.data.entity.TvshowEntity
import com.ajijetpackpro.finalsub.data.repository.MovieRepository

class FavoriteViewModel constructor(private val movieRepository: MovieRepository) : ViewModel() {

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> = movieRepository.getFavoriteMovies()

    fun getFavoriteTvShow(): LiveData<PagedList<TvshowEntity>> = movieRepository.getFavoriteTvShow()
}