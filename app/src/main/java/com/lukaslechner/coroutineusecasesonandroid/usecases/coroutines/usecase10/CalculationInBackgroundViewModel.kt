package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase10

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import timber.log.Timber
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

    suspend fun calculateFactorialOf(number: Int): BigInteger = withContext(Dispatchers.Default) {
        var factorial = BigInteger.ONE
        for (i in 1..number) {
            yield()
            factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
        }
        Timber.d("calculateFactorialOf: factorial completed!")
        return@withContext factorial
    }

    fun cancelCalculation() {
        viewModelScope.coroutineContext.cancelChildren()
    }

    //For this challenge you will be determining if you can create a palindrome from a list of numbers.
    // A palindrome is a string that can be equally read from left to right or right to left,
    // omitting blank spaces, and ignoring capitalization.
    // Examples of palindromes are:
    // "radar", "taco cat", and "Was it a cat I saw".
    // You will be given a list of numbers and you need to return a string that is a palindrome
    // using all of the numbers in the list.
    // If it is not possible to create a palindrome using all the numbers, return the string "Not possible".
    // If there are multiple palindromes, you may return any one of them.
    // The list will contain between 1 and 20 integers inclusive.
    // Each integer will be between 1 and 1000 inclusive.
    // The list will always contain at least 1 number that has a prime number of digits.

    //example
    //input: 1, 2, 3, 4, 5, 6, 7, 8, 9
    //output: 987654321
    //input: 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4
    //output: 443322111
    //code

    fun createPalindrome(numbers: List<Int>): String {
        val map = mutableMapOf<Int, Int>()
        var palindrome = ""
        numbers.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }
        val sorted = map.toList().sortedByDescending { (_, value) -> value }.toMap()
        var odd = 0
        sorted.forEach { (key, value) ->
            if (value % 2 != 0) {
                odd++
            }
        }
        if (odd > 1) {
            return "Not possible"
        }
        sorted.forEach { (key, value) ->
            if (value % 2 == 0) {
                repeat(value / 2) {
                    palindrome += key
                }
            }
        }
        sorted.forEach { (key, value) ->
            if (value % 2 != 0) {
                repeat(value) {
                    palindrome += key
                }
            }
        }
        sorted.forEach { (key, value) ->
            if (value % 2 == 0) {
                repeat(value / 2) {
                    palindrome += key
                }
            }
        }
        return palindrome
    }

}


