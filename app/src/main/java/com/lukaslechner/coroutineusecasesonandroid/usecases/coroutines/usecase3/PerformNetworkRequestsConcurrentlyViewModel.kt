package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase3

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class PerformNetworkRequestsConcurrentlyViewModel(
    private val mockApi: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequestsSequentially() {
        uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val oreoFeatures = mockApi.getAndroidVersionFeatures(27)
                val pieFeatures = mockApi.getAndroidVersionFeatures(28)
                val android10Features = mockApi.getAndroidVersionFeatures(29)

                val features = listOf(oreoFeatures, pieFeatures, android10Features)
                uiState.value = UiState.Success(features)

            }catch (e: Exception) {
                uiState.value = UiState.Error("Network request failed")
            }
        }

    }

    fun performNetworkRequestsConcurrently() = viewModelScope.launch {
        uiState.value = UiState.Loading
        val deferredOreoFeatures = viewModelScope.async { mockApi.getAndroidVersionFeatures(27) }
        val deferredPieFeatures = viewModelScope.async { mockApi.getAndroidVersionFeatures(28) }
        val deferredAndroid10Features = viewModelScope.async { mockApi.getAndroidVersionFeatures(29) }

        val features = awaitAll(deferredOreoFeatures, deferredPieFeatures, deferredAndroid10Features)
        uiState.value = UiState.Success(features)
    }
}