package com.ajijetpackpro.finalsub.ui.home.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ajijetpackpro.finalsub.data.entity.MovieEntity
import com.ajijetpackpro.finalsub.data.repository.MovieRepository
import com.ajijetpackpro.finalsub.vo.Resource

class MovieViewModel(private val movieRepository: MovieRepository): ViewModel() {
    fun getPopularMovies(): LiveData<Resource<PagedList<MovieEntity>>> = movieRepository.getPopularMovies()
}