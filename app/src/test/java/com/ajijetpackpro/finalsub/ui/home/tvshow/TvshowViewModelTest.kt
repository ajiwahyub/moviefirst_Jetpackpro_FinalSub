package com.ajijetpackpro.finalsub.ui.home.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.ajijetpackpro.finalsub.data.entity.MovieEntity
import com.ajijetpackpro.finalsub.data.entity.TvshowEntity
import com.ajijetpackpro.finalsub.data.repository.MovieRepository
import com.ajijetpackpro.finalsub.ui.home.movie.MovieViewModel
import com.ajijetpackpro.finalsub.vo.Resource
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvshowViewModelTest{

    private lateinit var viewModel: TvshowViewModel

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvshowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvshowEntity>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = TvshowViewModel(movieRepository)
    }

    @Test
    fun getPopularTvshows() {
        val tvshowDummy= Resource.success(pagedList)
        Mockito.`when`(tvshowDummy.data?.size).thenReturn(1)
        val tvshow = MutableLiveData<Resource<PagedList<TvshowEntity>>>()
        tvshow.value = tvshowDummy

        Mockito.`when`(movieRepository.getPopularTvshows()).thenReturn(tvshow)
        val tvshowEntities = viewModel.getPopularTvshow().value?.data
        Mockito.verify(movieRepository).getPopularTvshows()
        assertNotNull(tvshowEntities)
        assertEquals(1, tvshowEntities?.size)

        viewModel.getPopularTvshow().observeForever(observer)
        Mockito.verify(observer).onChanged(tvshowDummy)
    }
}