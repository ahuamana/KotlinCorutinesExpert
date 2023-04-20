package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase7

import androidx.lifecycle.viewModelScope
import androidx.work.ListenableWorker.Result.retry
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import timber.log.Timber

class TimeoutAndRetryViewModel(
    private val api: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequest() {
        uiState.value = UiState.Loading
        val numberOfRetries = 2
        val timeout = 1000L

        val oreoVersionsDeferred = viewModelScope.async {
           retry(numberOfRetries, timeout) {
                api.getAndroidVersionFeatures(27)
            }
        }

        val pieVersionsDeferred = viewModelScope.async {
            retry(numberOfRetries, timeout) {
               api.getAndroidVersionFeatures(28)
            }
        }

        viewModelScope.launch {
            try {
                val versionFeatures = listOf(
                    oreoVersionsDeferred,
                    pieVersionsDeferred
                ).awaitAll()
                uiState.value = UiState.Success(versionFeatures)
            } catch (e: Exception) {
                uiState.value = UiState.Error("Network request failed")
            }
        }

    }

    private suspend fun <T> retry(
        numberOfRetries: Int,
        delayBetweenRetries: Long = 100,
        block: suspend () -> T
    ): T {
        repeat(numberOfRetries) { retryCount ->
            try {
                return block()
            } catch (e: Exception) {
                Timber.e("Network request failed")
            }
            delay(delayBetweenRetries)
        }
        return block()
    }

    private suspend fun retryWithTimeout(
        numberOfRetries: Int,
        timeout: Long,
        block: suspend () -> Unit
    ) {
        repeat(numberOfRetries) { retryCount ->
            withTimeout(timeout) {
                block()
            }
        }
    }
}