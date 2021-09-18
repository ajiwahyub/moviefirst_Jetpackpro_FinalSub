package com.ajijetpackpro.finalsub.data.response

import com.google.gson.annotations.SerializedName

data class TvshowResponse(
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("name")
    var title: String? = null,

    @SerializedName("first_air_date")
    var rDate: String? = null,

    @SerializedName("vote_average")
    var uScore: Double? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("poster_path")
    var poster: String? = null,

    @SerializedName("backdrop_path")
    var background: String? = null,


)
