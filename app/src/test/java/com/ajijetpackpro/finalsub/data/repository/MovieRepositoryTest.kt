package com.ajijetpackpro.finalsub.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.ajijetpackpro.finalsub.data.entity.MovieEntity
import com.ajijetpackpro.finalsub.data.entity.TvshowEntity
import com.ajijetpackpro.finalsub.data.local.LocalDataSource
import com.ajijetpackpro.finalsub.data.remote.RemoteDataSource
import com.ajijetpackpro.finalsub.dummydata.MovieDummy
import com.ajijetpackpro.finalsub.utils.AppExecutors

import com.ajijetpackpro.finalsub.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)
    private val movieRepository = FakeMovieRepository(remote, local, appExecutors)

    private val listMovie = MovieDummy.getDummyMovie()
    private val listTvShow = MovieDummy.getDummyTvshow()

    private val movieId = listMovie[0].id
    private val tvshowId = listTvShow[0].id

    private val detailMovie = MovieDummy.getDummyDetailMovie()[0]
    private val detailTvShow = MovieDummy.getDummyDetailTvshow()[0]

    @Test
    fun getPopularMovies() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getMovieList()).thenReturn(dataSourceFactory)
        movieRepository.getPopularMovies()

        val showEntity = Resource.success(PagedListUtil.mockPagedList(MovieDummy.getDummyMovie()))
        verify(local).getMovieList()
        assertNotNull(showEntity.data)
        assertEquals(listTvShow.size.toLong(), showEntity.data?.size?.toLong())
    }

    @Test
    fun getPopularTvshows() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvshowEntity>
        Mockito.`when`(local.getTvshowList()).thenReturn(dataSourceFactory)
        movieRepository.getPopularTvshows()

        val showEntity = Resource.success(PagedListUtil.mockPagedList(MovieDummy.getDummyTvshow()))
        verify(local).getTvshowList()
        assertNotNull(showEntity.data)
        assertEquals(listTvShow.size.toLong(), showEntity.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() {
        val detailMovieDummy = MutableLiveData<MovieEntity>()
        detailMovieDummy.value = detailMovie
        Mockito.`when`(local.getDetailMovieById(movieId!!)).thenReturn(detailMovieDummy)

        val movieEntity = LiveDataTestUtil.getValue(movieRepository.getMovieDetail(detailMovie.id!!))
        verify(local).getDetailMovieById(movieId)
        assertNotNull(movieEntity)
        assertEquals(detailMovie.id, movieEntity.data?.id)
    }

    @Test
    fun getTvshowDetail() {
        val detailTvshowDummy = MutableLiveData<TvshowEntity>()
        detailTvshowDummy.value = detailTvShow
        Mockito.`when`(local.getDetailTvshowsById(tvshowId!!)).thenReturn(detailTvshowDummy)

        val tvshowEntity = LiveDataTestUtil.getValue(movieRepository.getTvshowDetail(detailTvShow.id!!))
        verify(local).getDetailTvshowsById(tvshowId)
        assertNotNull(tvshowEntity)
        assertEquals(detailTvShow.id, tvshowEntity.data?.id)
    }

    @Test
    fun getFavoriteMovies() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getFavoriteMovie()).thenReturn(dataSourceFactory)
        movieRepository.getFavoriteMovies()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(MovieDummy.getDummyMovie()))
        Mockito.verify(local).getFavoriteMovie()
        assertNotNull(movieEntity.data)
        assertEquals(listMovie.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getFavoriteTvShow() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvshowEntity>
        Mockito.`when`(local.getFavoriteTvshow()).thenReturn(dataSourceFactory)
        movieRepository.getFavoriteTvShow()

        val tvShowEntity = Resource.success(PagedListUtil.mockPagedList(MovieDummy.getDummyTvshow()))
        Mockito.verify(local).getFavoriteTvshow()
        assertNotNull(tvShowEntity.data)
        assertEquals(listTvShow.size.toLong(), tvShowEntity.data?.size?.toLong())
    }

}