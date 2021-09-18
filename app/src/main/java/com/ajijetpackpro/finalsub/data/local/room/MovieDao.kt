package com.ajijetpackpro.finalsub.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.ajijetpackpro.finalsub.data.entity.MovieEntity
import com.ajijetpackpro.finalsub.data.entity.TvshowEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentities")
    fun getMovieList(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieentities where isFavorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity>

    @Transaction
    @Query("SELECT * FROM movieentities WHERE movieId = :movieId")
    fun getDetailMovieById(movieId: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movie: List<MovieEntity>)

    @Update
    fun updateMovieList(movie: MovieEntity)

    @Query("SELECT * FROM tvshowentities")
    fun getTvshowsList(): DataSource.Factory<Int, TvshowEntity>

    @Query("SELECT * FROM tvshowentities where isFavorite = 1")
    fun getFavoriteTvshow(): DataSource.Factory<Int, TvshowEntity>

    @Query("SELECT * FROM tvshowentities WHERE tvshowId = :tvshowId")
    fun getDetailTvshowById(tvshowId: Int): LiveData<TvshowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvshows(tvshow: List<TvshowEntity>)

    @Update
    fun updateTvshowList(tvshow: TvshowEntity)

}