package com.keremturker.coronavirusapi.ui.fragment.list

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.keremturker.coronavirusapi.repository.entity.request.CountriesDataRequest
import com.keremturker.coronavirusapi.repository.entity.response.CountriesDataResponse
import com.keremturker.coronavirusapi.repository.entity.response.CountriesResponseItem
import com.keremturker.coronavirusapi.repository.network.APIClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class ListVM
@Inject constructor(
    private val client: APIClient,
    val app: Application
) : AndroidViewModel(app) {

    private val _onGetCountriesSuccessLD = MutableLiveData<List<CountriesResponseItem>>()
    val onGetCountriesLD: LiveData<List<CountriesResponseItem>> get() = _onGetCountriesSuccessLD


    fun getList() {

        viewModelScope.launch {
            var result: Result<CountriesDataResponse>? = null
            try {
                result =
                    withContext(Dispatchers.IO) {
                        Result.runCatching {
                            (client.apiCollect.countriesData())
                        }
                    }

                if (result.exceptionOrNull() == null) {
                    result.getOrNull()?.let { objectT ->
                        onGetCountries(objectT.result)
                    }
                } else {
                    val resultException = result.exceptionOrNull()
                    val errorMessage = resultException?.message
                    if (resultException != null) {
                        onError(errorMessage)
                    }
                }

            } catch (e: Exception) {
                onError(result?.exceptionOrNull()?.message)
            }
        }
    }

    private fun onGetCountries(response: List<CountriesResponseItem>?) {
        response?.let {
            _onGetCountriesSuccessLD.postValue(it)
        }
    }

    private fun onError(message: String?) {
        Log.d("test123", message ?: "Error")
    }
}