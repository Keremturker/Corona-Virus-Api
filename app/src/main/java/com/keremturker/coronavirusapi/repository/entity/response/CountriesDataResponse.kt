package com.keremturker.coronavirusapi.repository.entity.response


import com.google.gson.annotations.SerializedName

data class CountriesDataResponse(
    @SerializedName("result")
    val result: List<CountriesResponseItem>?,
    @SerializedName("success")
    val success: Boolean
)