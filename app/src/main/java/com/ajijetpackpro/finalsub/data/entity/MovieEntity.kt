package com.ajijetpackpro.finalsub.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieentities")
data class MovieEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    var id: Int? = 0,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "rDate")
    var rDate: String? = null,

    @ColumnInfo(name = "uScore")
    var uScore: Double? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "poster")
    var poster: String? = null,

    @ColumnInfo(name = "background")
    var background: String? = null,


    @NonNull
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)