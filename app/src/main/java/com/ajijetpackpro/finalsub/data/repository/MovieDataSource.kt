package com.ajijetpackpro.finalsub.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.ajijetpackpro.finalsub.data.entity.MovieEntity
import com.ajijetpackpro.finalsub.data.entity.TvshowEntity
import com.ajijetpackpro.finalsub.vo.Resource

interface MovieDataSource {

    fun getPopularMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getPopularTvshows(): LiveData<Resource<PagedList<TvshowEntity>>>

    fun getMovieDetail(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getTvshowDetail(tvshowId:Int): LiveData<Resource<TvshowEntity>>

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getFavoriteTvShow(): LiveData<PagedList<TvshowEntity>>

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean)

    fun setFavoriteTvShow(tvShow: TvshowEntity, newState: Boolean)
}