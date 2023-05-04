package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase10

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger
import kotlin.system.measureTimeMillis

class CalculationInBackgroundViewModel : BaseViewModel<UiState>() {

    //IO Distpacher
    // is used for blocking IO operations
    // such as reading or writing from files, making network requests, or working with databases
    // It uses a shared pool of threads
    // 64 threads by default

    // Default Dispatcher
    // is used for CPU-intensive work
    // such as sorting large lists, parsing JSON, or doing complex calculations
    // It uses a shared pool of threads
    // 2 * number of CPU cores by default

    // Main Dispatcher
    // is used for UI operations
    // such as updating the UI with the result of a background operation
    // It uses a single thread
    // the main thread by default

    // Unconfined Dispatcher
    // is used for operations that don't have any specific thread requirements
    // It uses the thread that called the suspend function


    fun performCalculation(factorialOf: Int) {
        uiState.value = UiState.Loading
        viewModelScope.launch(context = Dispatchers.Default) {
            var result: BigInteger = BigInteger.ONE
            val computationDuration = measureTimeMillis {
                val result = calculateFactorialOf(factorialOf)
            }
            var resultString = ""
            val strinConvertsionDuration = measureTimeMillis {
                resultString = result.toString()
            }
            withContext(Dispatchers.Main) {
                uiState.value = UiState.Success(resultString, computationDuration, strinConvertsionDuration)
            }
         }

    }

    private fun calculateFactorialOf(number: Int): BigInteger {
        var factorial = BigInteger.ONE
        for (i in 1..number) {
           factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
        }
        return factorial
    }
}