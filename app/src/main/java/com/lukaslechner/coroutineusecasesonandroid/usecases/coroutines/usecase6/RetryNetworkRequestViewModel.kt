package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase6

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.launch
import timber.log.Timber

class RetryNetworkRequestViewModel(
    private val api: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequest() {
        uiState.value = UiState.Loading
        viewModelScope.launch { // <--- viewModelScope
            val numberOfRetries = 3
            repeat(numberOfRetries) { retryCount ->
                try {
                    retry(numberOfRetries) {
                        val recentAndroidVersions = api.getRecentAndroidVersions()
                        uiState.value = UiState.Success(recentAndroidVersions)
                    }
                    return@launch
                } catch (e: Exception) {
                    if (retryCount == numberOfRetries - 1) {
                        uiState.value = UiState.Error("Network request failed")
                    }
                }
            }
        }
    }


    suspend fun <T> retry(numberOfRetries:Int, block: suspend () -> T ):T {
        repeat(numberOfRetries){ retryCount ->
            try {
                return block()
            } catch (e: Exception) {
                Timber.e("Network request failed")
            }
        }
        return block()
    }

}