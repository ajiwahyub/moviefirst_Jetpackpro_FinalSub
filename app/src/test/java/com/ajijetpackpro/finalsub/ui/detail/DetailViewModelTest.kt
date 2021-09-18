package com.ajijetpackpro.finalsub.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ajijetpackpro.finalsub.data.entity.MovieEntity
import com.ajijetpackpro.finalsub.data.entity.TvshowEntity
import com.ajijetpackpro.finalsub.data.repository.LiveDataTestUtil
import com.ajijetpackpro.finalsub.data.repository.MovieRepository
import com.ajijetpackpro.finalsub.dummydata.MovieDummy
import com.ajijetpackpro.finalsub.vo.Resource
import com.ajijetpackpro.finalsub.vo.Status
import com.nhaarman.mockitokotlin2.verify
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private val movieDummyDetail = MovieDummy.getDummyDetailMovie()[0]
    private val tvshowDummyDetail = MovieDummy.getDummyDetailTvshow()[0]
    private val movieId = movieDummyDetail.id
    private val tvshowId = tvshowDummyDetail.id

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieRepository)
    }

    @Test
    fun getDetailMovieById() {
        val dummyMovie = MutableLiveData<Resource<MovieEntity>>()
        val resource = Resource.success(movieDummyDetail)
        dummyMovie.value = resource
        Mockito.`when`(movieRepository.getMovieDetail(movieId!!)).thenReturn(dummyMovie)

        val detailMovie = viewModel.getDetailMovieById(movieId).value
        verify(movieRepository).getMovieDetail(movieId)

        if (detailMovie?.status == Status.SUCCESS) {
            detailMovie.data?.let {
                Assert.assertNotNull(detailMovie)
                assertEquals(movieDummyDetail.id, it.id)
                assertEquals(movieDummyDetail.title, it.title)
                assertEquals(movieDummyDetail.rDate, it.rDate)
                assertEquals(movieDummyDetail.uScore, it.uScore)
                assertEquals(movieDummyDetail.overview, it.overview)
                assertEquals(movieDummyDetail.poster, it.poster)
                assertEquals(movieDummyDetail.background, it.background)
            }
        }

        val observer = mock(Observer::class.java) as Observer<Resource<MovieEntity>>
        viewModel.getDetailMovieById(movieId).observeForever(observer)
        verify(observer).onChanged(resource)
    }

    @Test
    fun getDetailTvShowById() {
        val dummyTvshow = MutableLiveData<Resource<TvshowEntity>>()
        val resource = Resource.success(tvshowDummyDetail)
        dummyTvshow.value = resource
        Mockito.`when`(movieRepository.getTvshowDetail(tvshowId!!)).thenReturn(dummyTvshow)

        val detailTvshow = viewModel.getDetailTvShowById(tvshowId).value
        verify(movieRepository).getTvshowDetail(tvshowId)

        if (detailTvshow?.status == Status.SUCCESS) {
            detailTvshow.data?.let {
                Assert.assertNotNull(detailTvshow)
                assertEquals(tvshowDummyDetail.id, it.id)
                assertEquals(tvshowDummyDetail.title, it.title)
                assertEquals(tvshowDummyDetail.rDate, it.rDate)
                assertEquals(tvshowDummyDetail.uScore, it.uScore)
                assertEquals(tvshowDummyDetail.overview, it.overview)
                assertEquals(tvshowDummyDetail.poster, it.poster)
                assertEquals(tvshowDummyDetail.background, it.background)
            }
        }

        val observer = mock(Observer::class.java) as Observer<Resource<TvshowEntity>>
        viewModel.getDetailTvShowById(tvshowId).observeForever(observer)
        verify(observer).onChanged(resource)
    }

    @Test
    fun addFavoriteMovie(){
        val dummmyMovie = MutableLiveData<Resource<MovieEntity>>()
        val resource = Resource.success(movieDummyDetail)
        dummmyMovie.value = resource
        Mockito.`when`(movieRepository.getMovieDetail(movieId!!)).thenReturn(dummmyMovie)

        val statusFavorite = true
        movieRepository.setFavoriteMovie(movieDummyDetail, statusFavorite)

        val expectedResult = movieDummyDetail
        expectedResult.isFavorite = statusFavorite
        viewModel.setFavoriteMovie(movieDummyDetail)

        val byId = LiveDataTestUtil.getValue(movieRepository.getMovieDetail(movieDummyDetail.id!!))
        MatcherAssert.assertThat(byId.data, IsEqual.equalTo(expectedResult))
    }

    @Test
    fun addFavoriteTvshow(){
        val dummmyTvshow = MutableLiveData<Resource<TvshowEntity>>()
        val resource = Resource.success(tvshowDummyDetail)
        dummmyTvshow.value = resource
        Mockito.`when`(movieRepository.getTvshowDetail(tvshowId!!)).thenReturn(dummmyTvshow)

        val statusFavorite = true
        movieRepository.setFavoriteTvShow(tvshowDummyDetail, statusFavorite)

        val expectedResult = tvshowDummyDetail
        expectedResult.isFavorite = statusFavorite
        viewModel.setFavoriteTvshow(tvshowDummyDetail)

        val byId = LiveDataTestUtil.getValue(movieRepository.getTvshowDetail(tvshowDummyDetail.id!!))
        MatcherAssert.assertThat(byId.data, IsEqual.equalTo(expectedResult))
    }
    @Test
    fun removeFavoriteMovie(){
        val dummyMovie = MutableLiveData<Resource<MovieEntity>>()
        val resource = Resource.success(movieDummyDetail)
        dummyMovie.value = resource
        Mockito.`when`(movieRepository.getMovieDetail(movieId!!)).thenReturn(dummyMovie)

        val statusFavorite = false
        movieRepository.setFavoriteMovie(movieDummyDetail, statusFavorite)

        val expectedResult = movieDummyDetail
        expectedResult.isFavorite = statusFavorite
        viewModel.setFavoriteMovie(movieDummyDetail)

        val byId = LiveDataTestUtil.getValue(movieRepository.getMovieDetail(movieDummyDetail.id!!))
        MatcherAssert.assertThat(byId.data, IsEqual.equalTo(expectedResult))
    }

    @Test
    fun removeFavoriteTvshow(){
        val dummyTvshow = MutableLiveData<Resource<TvshowEntity>>()
        val resource = Resource.success(tvshowDummyDetail)
        dummyTvshow.value = resource
        Mockito.`when`(movieRepository.getTvshowDetail(tvshowId!!)).thenReturn(dummyTvshow)

        val statusFavorite = false
        movieRepository.setFavoriteTvShow(tvshowDummyDetail, statusFavorite)

        val expectedResult = tvshowDummyDetail
        expectedResult.isFavorite = statusFavorite
        viewModel.setFavoriteTvshow(tvshowDummyDetail)

        val byId = LiveDataTestUtil.getValue(movieRepository.getTvshowDetail(tvshowDummyDetail.id!!))
        MatcherAssert.assertThat(byId.data, IsEqual.equalTo(expectedResult))
    }
}