package com.ajijetpackpro.finalsub.ui.home.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.ajijetpackpro.finalsub.data.entity.MovieEntity
import com.ajijetpackpro.finalsub.data.repository.MovieRepository
import com.ajijetpackpro.finalsub.vo.Resource
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getPopularMovies() {
        val movieDummy= Resource.success(pagedList)
        Mockito.`when`(movieDummy.data?.size).thenReturn(1)
        val movie = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movie.value = movieDummy

        Mockito.`when`(movieRepository.getPopularMovies()).thenReturn(movie)
        val movieEntities = viewModel.getPopularMovies().value?.data
        Mockito.verify(movieRepository).getPopularMovies()
        assertNotNull(movieEntities)
        assertEquals(1, movieEntities?.size)

        viewModel.getPopularMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(movieDummy)
    }
}