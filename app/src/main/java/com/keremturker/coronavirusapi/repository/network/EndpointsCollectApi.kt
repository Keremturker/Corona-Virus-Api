package com.keremturker.coronavirusapi.repository.network

import com.keremturker.coronavirusapi.repository.entity.request.CountriesDataRequest
import com.keremturker.coronavirusapi.repository.entity.response.CountriesDataResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EndpointsCollectApi {

    @POST("/corona/countriesData")
    suspend fun countriesData(@Body request: CountriesDataRequest = CountriesDataRequest("")): CountriesDataResponse

}