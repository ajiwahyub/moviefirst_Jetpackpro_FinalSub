package com.ajijetpackpro.finalsub.ui.home.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.ajijetpackpro.finalsub.data.entity.MovieEntity
import com.ajijetpackpro.finalsub.data.entity.TvshowEntity
import com.ajijetpackpro.finalsub.data.repository.MovieRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observerMovie: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var observerTvShow: Observer<PagedList<TvshowEntity>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var tvShowPagedList: PagedList<TvshowEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(movieRepository)
    }

    @Test
    fun getListFavoriteMovie() {

        val movieDummy = moviePagedList
        Mockito.`when`(movieDummy.size).thenReturn(7)
        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = movieDummy

        Mockito.`when`(movieRepository.getFavoriteMovies()).thenReturn(movie)
        val movieEntity = viewModel.getFavoriteMovie().value
        Mockito.verify(movieRepository).getFavoriteMovies()
        Assert.assertNotNull(movieEntity)
        Assert.assertEquals(7, movieEntity?.size)

        viewModel.getFavoriteMovie().observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(movieDummy)

    }

    @Test
    fun getListFavoriteTvShow() {
        val tvshowDummy = tvShowPagedList
        Mockito.`when`(tvshowDummy.size).thenReturn(7)
        val tvShow = MutableLiveData<PagedList<TvshowEntity>>()
        tvShow.value = tvshowDummy

        Mockito.`when`(movieRepository.getFavoriteTvShow()).thenReturn(tvShow)
        val tvShowEntity = viewModel.getFavoriteTvShow().value
        Mockito.verify(movieRepository).getFavoriteTvShow()
        Assert.assertNotNull(tvShowEntity)
        Assert.assertEquals(7, tvShowEntity?.size)

        viewModel.getFavoriteTvShow().observeForever(observerTvShow)
        Mockito.verify(observerTvShow).onChanged(tvshowDummy)
    }
}
