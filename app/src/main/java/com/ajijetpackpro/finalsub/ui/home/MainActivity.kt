package com.ajijetpackpro.finalsub.ui.home

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.ajijetpackpro.finalsub.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    var titleapp = "Movie First"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setNavigationView()
        setActionBar(titleapp)

    }

    private fun setNavigationView() {
        val navView = findViewById<BottomNavigationView>(R.id.bot_nav_bar)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(navView, navController)
    }

    private fun setActionBar(titleapp: String) {
        val icon = ResourcesCompat.getDrawable(resources, R.drawable.ic_tmdb, null)?.toBitmap()
        val fixedIcon = BitmapDrawable(resources,
            icon.let { Bitmap.createScaledBitmap(it!!, 60, 60, true) })
        supportActionBar?.setHomeAsUpIndicator(fixedIcon)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = titleapp
        }
    }
}