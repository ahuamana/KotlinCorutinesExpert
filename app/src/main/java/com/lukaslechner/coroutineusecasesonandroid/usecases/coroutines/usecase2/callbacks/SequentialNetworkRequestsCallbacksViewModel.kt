package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase2.callbacks

import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.AndroidVersion
import com.lukaslechner.coroutineusecasesonandroid.mock.VersionFeatures
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SequentialNetworkRequestsCallbacksViewModel(
    private val mockApi: CallbackMockApi = mockApi()
) : BaseViewModel<UiState>() {

    private var getAndroidVersionCallback :Call<List<AndroidVersion>> ?= null
    private var getAndroidVersionFeaturesCallback :Call<VersionFeatures> ?= null



    fun perform2SequentialNetworkRequest() {
        uiState.value = UiState.Loading

        getAndroidVersionCallback = mockApi.getRecentAndroidVersions()
        getAndroidVersionCallback!!.enqueue(object : Callback<List<AndroidVersion>> {
            override fun onResponse(
                call: Call<List<AndroidVersion>>,
                response: Response<List<AndroidVersion>>
            ) {
                if(response.isSuccessful){
                    val recentVersions = response.body()!!.last()
                    getAndroidVersionFeaturesCallback = mockApi.getAndroidVersionFeatures(recentVersions.apiLevel)
                    getAndroidVersionFeaturesCallback!!.enqueue(object : Callback<VersionFeatures> {
                        override fun onResponse(
                            call: Call<VersionFeatures>,
                            response: Response<VersionFeatures>
                        ) {
                            if(response.isSuccessful){
                                val versionFeatures = response.body()!!
                                uiState.value = UiState.Success(versionFeatures)
                            }else{
                                uiState.value = UiState.Error("Network request failature")
                            }
                        }

                        override fun onFailure(call: Call<VersionFeatures>, t: Throwable) {
                            uiState.value = UiState.Error("Unknown error")
                        }

                    })

                }else{
                    uiState.value = UiState.Error("Network request failature")
                }
            }

            override fun onFailure(call: Call<List<AndroidVersion>>, t: Throwable) {
                uiState.value = UiState.Error("Unknown error")
            }

        })
    }

    override fun onCleared() {
        super.onCleared()
        getAndroidVersionCallback?.cancel()
        getAndroidVersionFeaturesCallback?.cancel()
    }
}