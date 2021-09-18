package com.ajijetpackpro.finalsub.utils

import android.widget.ImageView
import com.ajijetpackpro.finalsub.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadFromUrl(path: String) {
    Glide.with(this).clear(this)
    Glide.with(this)
        .setDefaultRequestOptions(
            RequestOptions()
            .placeholder(R.drawable.ic_tmdb)
            .error(R.drawable.ic_tmdb)
        ).load(path).into(this)
}