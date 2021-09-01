package com.keremturker.coronavirusapi.ui.fragment.list

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.keremturker.coronavirusapi.repository.entity.request.CountriesDataRequest
import com.keremturker.coronavirusapi.repository.entity.response.CountriesDataResponse
import com.keremturker.coronavirusapi.repository.network.APIClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ListVM @Inject constructor(private val client: APIClient, app: Application) :
    AndroidViewModel(app) {

    fun getList() {

        viewModelScope.launch {


            var result: Result<CountriesDataResponse>? = null

            try {
                result = withContext(Dispatchers.IO) {
                    Result.runCatching {
                        (client.apiCollect.countriesData(
                            CountriesDataRequest("Turkey")
                        ))
                    }
                }

                if (result.exceptionOrNull() == null) {

                    result.getOrNull()?.let {

                        Log.d("test123", it.result.toString())
                    }
                } else {
                    Log.d("test123", result.toString())

                }
                Log.d("test123", result.toString())

            } catch (e: Exception) {
                Log.d("test123", e.toString())

            }
        }
    }

}