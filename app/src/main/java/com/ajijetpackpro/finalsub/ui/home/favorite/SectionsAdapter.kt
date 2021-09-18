package com.ajijetpackpro.finalsub.ui.home.favorite

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ajijetpackpro.finalsub.R
import com.ajijetpackpro.finalsub.ui.home.favorite.movie.FavMovieFragment
import com.ajijetpackpro.finalsub.ui.home.favorite.tvshow.FavTvshowFragment

class SectionsAdapter (private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val TAB_TITLE = intArrayOf(
        R.string.tabmovie,
        R.string.tabtvshos
    )
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FavMovieFragment()
            1 -> fragment = FavTvshowFragment()
        }

        return  fragment as Fragment
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLE[position])
    }

}