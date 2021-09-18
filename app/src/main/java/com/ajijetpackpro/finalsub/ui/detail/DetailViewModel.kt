package com.ajijetpackpro.finalsub.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ajijetpackpro.finalsub.data.entity.MovieEntity
import com.ajijetpackpro.finalsub.data.entity.TvshowEntity
import com.ajijetpackpro.finalsub.data.repository.MovieRepository
import com.ajijetpackpro.finalsub.vo.Resource


class DetailViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun getDetailMovieById(movieId: Int): LiveData<Resource<MovieEntity>> = movieRepository.getMovieDetail(movieId)

    fun getDetailTvShowById(tvshowId: Int): LiveData<Resource<TvshowEntity>> = movieRepository.getTvshowDetail(tvshowId)


    fun setFavoriteMovie(movie: MovieEntity) {
            val newState = !movie.isFavorite
            movieRepository.setFavoriteMovie(movie, newState)
        }
    fun setFavoriteTvshow(tvshow: TvshowEntity) {
            val newState = !tvshow.isFavorite
            movieRepository.setFavoriteTvShow(tvshow, newState)
        }

}




