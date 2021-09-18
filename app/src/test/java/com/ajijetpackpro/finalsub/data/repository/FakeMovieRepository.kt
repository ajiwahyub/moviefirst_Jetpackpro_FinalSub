package com.ajijetpackpro.finalsub.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ajijetpackpro.finalsub.data.entity.MovieEntity
import com.ajijetpackpro.finalsub.data.entity.TvshowEntity
import com.ajijetpackpro.finalsub.data.local.LocalDataSource
import com.ajijetpackpro.finalsub.data.remote.ApiResponse
import com.ajijetpackpro.finalsub.data.remote.RemoteDataSource
import com.ajijetpackpro.finalsub.data.response.MovieResponse
import com.ajijetpackpro.finalsub.data.response.TvshowItem
import com.ajijetpackpro.finalsub.data.response.TvshowResponse
import com.ajijetpackpro.finalsub.utils.AppExecutors
import com.ajijetpackpro.finalsub.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.ArrayList

class FakeMovieRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors) : MovieDataSource {

    override fun getPopularMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object: NetworkBoundResource<PagedList<MovieEntity>, List<com.ajijetpackpro.finalsub.data.response.MovieItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovieList(), config).build()
            }
            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<com.ajijetpackpro.finalsub.data.response.MovieItem>>> =
                remoteDataSource.getPopularMovies()

            public override fun saveCallResult(data: List<com.ajijetpackpro.finalsub.data.response.MovieItem>) {
                    val movieList = ArrayList<MovieEntity>()
                    for (response in data) {
                        val movie = MovieEntity(
                            response.id,
                            response.title,
                            response.rDate,
                            response.uScore,
                            null,
                            response.poster,
                            response.background

                        )
                        movieList.add(movie)
                    }
                    localDataSource.insertMovies(movieList)
                }
        }.asLiveData()
    }

    override fun getPopularTvshows(): LiveData<Resource<PagedList<TvshowEntity>>> {
        return object: NetworkBoundResource<PagedList<TvshowEntity>, List<TvshowItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<TvshowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getTvshowList(), config).build()
            }
            override fun shouldFetch(data: PagedList<TvshowEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TvshowItem>>> =
                remoteDataSource.getPopularTvshows()

            public override fun saveCallResult(data: List<TvshowItem>) {
                val tvshowList = ArrayList<TvshowEntity>()
                for (response in data) {
                    val tvshow = TvshowEntity(
                        response.id,
                        response.title,
                        response.rDate,
                        response.uScore,
                        null,
                        response.poster,
                        response.background


                    )
                    tvshowList.add(tvshow)
                }
                localDataSource.insertTvshows(tvshowList)
            }
        }.asLiveData()
    }


    override fun getMovieDetail(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object: NetworkBoundResource<MovieEntity, MovieResponse>(appExecutors) {
            public override fun loadFromDB(): LiveData<MovieEntity> = localDataSource.getDetailMovieById(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data?.overview == null

            public override fun createCall(): LiveData<ApiResponse<MovieResponse>> =
                remoteDataSource.getMovieDetail(movieId)

            public override fun saveCallResult(data: MovieResponse) {
                

                val movieItem = MovieEntity(
                    data.id,
                    data.title,
                    data.rDate,
                    data.uScore,
                    data.overview,
                    data.poster,
                    data.background

                )
                localDataSource.updateMovie(movieItem)
            }
        }.asLiveData()
    }

    override fun getTvshowDetail(tvshowId: Int): LiveData<Resource<TvshowEntity>> {
        return object: NetworkBoundResource<TvshowEntity, TvshowResponse>(appExecutors) {
            public override fun loadFromDB(): LiveData<TvshowEntity> = localDataSource.getDetailTvshowsById(tvshowId)

            override fun shouldFetch(data: TvshowEntity?): Boolean =
                data?.overview == null

            public override fun createCall(): LiveData<ApiResponse<TvshowResponse>> =
                remoteDataSource.getTvshowDetail(tvshowId)

            public override fun saveCallResult(data: TvshowResponse) {


                val tvshowItem = TvshowEntity(
                    data.id,
                    data.title,
                    data.rDate,
                    data.uScore,
                    data.overview,
                    data.poster,
                    data.background
                )
                localDataSource.updateTvShow(tvshowItem)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    override fun getFavoriteTvShow(): LiveData<PagedList<TvshowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvshow(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, newState: Boolean){
        CoroutineScope(IO).launch {
            localDataSource.setFavoriteMovie(movie, newState)
        }
    }

    override fun setFavoriteTvShow(tvShow: TvshowEntity, newState: Boolean){
        CoroutineScope(IO).launch {
            localDataSource.setFavoriteTvshows(tvShow, newState)
        }
    }


}