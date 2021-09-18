package com.ajijetpackpro.finalsub.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ajijetpackpro.finalsub.data.entity.MovieEntity
import com.ajijetpackpro.finalsub.data.entity.TvshowEntity

@Database(entities = [MovieEntity::class, TvshowEntity::class], version = 2, exportSchema = false)
abstract class MovieDatabase : RoomDatabase(){
    abstract fun movieDao(): MovieDao

    companion object {

        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "Movies.db")
                    .build().apply {
                    INSTANCE = this
                }
            }
    }
}