package com.keremturker.coronavirusapi.repository.entity.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
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