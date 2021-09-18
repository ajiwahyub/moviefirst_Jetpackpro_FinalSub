package com.ajijetpackpro.finalsub.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.ajijetpackpro.finalsub.data.entity.MovieEntity
import com.ajijetpackpro.finalsub.data.entity.TvshowEntity
import com.ajijetpackpro.finalsub.data.local.room.MovieDao

class LocalDataSource private constructor(private val mMovieDao: MovieDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)

    }

    fun getMovieList(): DataSource.Factory<Int, MovieEntity> = mMovieDao.getMovieList()

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity> =
        mMovieDao.getFavoriteMovie()

    fun getTvshowList(): DataSource.Factory<Int, TvshowEntity> = mMovieDao.getTvshowsList()

    fun getFavoriteTvshow(): DataSource.Factory<Int, TvshowEntity> =
        mMovieDao.getFavoriteTvshow()

    fun getDetailTvshowsById(tvshowId: Int): LiveData<TvshowEntity> =
        mMovieDao.getDetailTvshowById(tvshowId)

    fun getDetailMovieById(movieId: Int): LiveData<MovieEntity> =
        mMovieDao.getDetailMovieById(movieId)

    fun insertMovies(movies: List<MovieEntity>) {
        mMovieDao.insertMovies(movies)
    }
    fun insertTvshows(tvshows: List<TvshowEntity>) {
        mMovieDao.insertTvshows(tvshows)
    }



    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mMovieDao.updateMovieList(movie)
    }

    fun setFavoriteTvshows(tvshow: TvshowEntity, newState: Boolean) {
        tvshow.isFavorite = newState
        mMovieDao.updateTvshowList(tvshow)
    }



    fun updateMovie(movie: MovieEntity){
        mMovieDao.updateMovieList(movie)
    }

    fun updateTvShow(tvshow: TvshowEntity){
        mMovieDao.updateTvshowList(tvshow)
    }
}