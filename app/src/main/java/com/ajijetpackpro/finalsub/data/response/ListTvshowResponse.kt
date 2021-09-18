package com.ajijetpackpro.finalsub.data.response

import com.google.gson.annotations.SerializedName

data class ListTvshowResponse(
    @SerializedName("status_message")
    val statusMessage: String,
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("results")
    val result: List<TvshowItem>
)

data class TvshowItem(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("name")
    var title: String,

    @SerializedName("first_air_date")
    var rDate: String,

    @SerializedName("vote_average")
    var uScore: Double,

    @SerializedName("poster_path")
    var poster: String,

    @SerializedName("backdrop_path")
    var background: String
)
