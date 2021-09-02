package com.keremturker.coronavirusapi.ui.fragment.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.keremturker.coronavirusapi.repository.entity.response.CountriesDataResponse
import com.keremturker.coronavirusapi.repository.entity.response.CountriesResponseItem
import com.keremturker.coronavirusapi.repository.network.APIClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ListVM
@Inject constructor(
    private val client: APIClient,
    val app: Application
) : AndroidViewModel(app) {

    private val _onGetCountriesSuccessLD = MutableLiveData<MutableList<CountriesResponseItem>>()
    val onGetCountriesLD: LiveData<MutableList<CountriesResponseItem>> get() = _onGetCountriesSuccessLD

    private val _onLoading = MutableLiveData<Boolean>()
    val onLoading: LiveData<Boolean> get() = _onLoading

    init {
        getList()
    }

    fun getList() {
        _onLoading.postValue(true)
        viewModelScope.launch {
            val result: Result<CountriesDataResponse>?
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
                    onError()
                }

            } catch (e: Exception) {
                onError()
            }
        }
    }

    private fun onGetCountries(response: MutableList<CountriesResponseItem>?) {
        _onLoading.postValue(false)

        response?.let {
            _onGetCountriesSuccessLD.postValue(it)
        } ?: _onGetCountriesSuccessLD.postValue(mutableListOf())
    }

    private fun onError() {
        _onLoading.postValue(false)
        _onGetCountriesSuccessLD.postValue(mutableListOf())
    }
}