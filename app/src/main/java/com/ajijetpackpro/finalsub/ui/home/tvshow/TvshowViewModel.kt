package com.ajijetpackpro.finalsub.ui.home.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList

import com.ajijetpackpro.finalsub.data.entity.TvshowEntity
import com.ajijetpackpro.finalsub.data.repository.MovieRepository
import com.ajijetpackpro.finalsub.vo.Resource

class TvshowViewModel(private val movieRepository: MovieRepository): ViewModel() {
    fun getPopularTvshow(): LiveData<Resource<PagedList<TvshowEntity>>> = movieRepository.getPopularTvshows()
}