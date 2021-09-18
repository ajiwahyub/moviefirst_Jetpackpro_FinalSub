package com.ajijetpackpro.finalsub.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ajijetpackpro.finalsub.data.network.ApiConfig
import com.ajijetpackpro.finalsub.data.response.*
import com.ajijetpackpro.finalsub.utils.IdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getPopularMovies(): LiveData<ApiResponse<List<MovieItem>>> {
        IdlingResource.increment()
        val resultResponseMovie = MutableLiveData<ApiResponse<List<MovieItem>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val response = ApiConfig.getApiService().getPopularMovies().await()
                resultResponseMovie.postValue(ApiResponse.success(response.result))
            }catch (e: IOException){
                Log.e("getDiscoverMovie Error", e.message.toString())
                resultResponseMovie.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        IdlingResource.decrement()
        return  resultResponseMovie
    }

    fun getPopularTvshows(): LiveData<ApiResponse<List<TvshowItem>>> {
        IdlingResource.increment()
        val resultResponseTvshow = MutableLiveData<ApiResponse<List<TvshowItem>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val response = ApiConfig.getApiService().getPopularTvshows().await()
                resultResponseTvshow.postValue(ApiResponse.success(response.result))
            }catch (e: IOException){
                Log.e("getDiscoverMovie Error", e.message.toString())
                resultResponseTvshow.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        IdlingResource.decrement()
        return  resultResponseTvshow
    }

    fun getMovieDetail(movieId: Int): LiveData<ApiResponse<MovieResponse>> {
        IdlingResource.increment()
        val resultResponseTvshow = MutableLiveData<ApiResponse<MovieResponse>>()
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val response = ApiConfig.getApiService().getDetailMovie(movieId).await()
                resultResponseTvshow.postValue(ApiResponse.success(response))
            }catch (e: IOException){
                Log.e("getDiscoverMovie Error", e.message.toString())
                resultResponseTvshow.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        MovieResponse()
                    )
                )
            }
        }
        IdlingResource.decrement()
        return  resultResponseTvshow
    }

    fun getTvshowDetail(tvShowId: Int): LiveData<ApiResponse<TvshowResponse>> {
        IdlingResource.increment()
        val resultResponseTvshow = MutableLiveData<ApiResponse<TvshowResponse>>()
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val response = ApiConfig.getApiService().getDetailTvshow(tvShowId).await()
                resultResponseTvshow.postValue(ApiResponse.success(response))
            }catch (e: IOException){
                Log.e("getDiscoverMovie Error", e.message.toString())
                resultResponseTvshow.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        TvshowResponse()
                    )
                )
            }
        }
        IdlingResource.decrement()
        return  resultResponseTvshow
    }
}