package com.ajijetpackpro.finalsub.di

import android.content.Context
import com.ajijetpackpro.finalsub.data.local.LocalDataSource
import com.ajijetpackpro.finalsub.data.local.room.MovieDatabase
import com.ajijetpackpro.finalsub.data.remote.RemoteDataSource
import com.ajijetpackpro.finalsub.data.repository.MovieRepository
import com.ajijetpackpro.finalsub.utils.AppExecutors

object MovieInjection {

    fun provideMovieRepository(context: Context): MovieRepository {
        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}