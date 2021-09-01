package com.keremturker.coronavirusapi.repository.entity.response


 import com.google.gson.annotations.SerializedName
 import java.io.Serializable

data class CountriesResponseItem(
    @SerializedName("activeCases")
    val activeCases: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("newCases")
    val newCases: String,
    @SerializedName("newDeaths")
    val newDeaths: String,
    @SerializedName("totalRecovered")
    val totalRecovered: String,
    @SerializedName("totalcases")
    val totalcases: String,
    @SerializedName("totaldeaths")
    val totaldeaths: String
) : Serializable