package com.lukaslechner.coroutineusecasesonandroid.playground.struturedconcurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

val scope = CoroutineScope(Dispatchers.Default)

fun main() = runBlocking{
    val job = scope.launch {
        delay(1000)
        println("Coroutine started")
    }
    job.invokeOnCompletion {
        throwable -> if (throwable != null) {
            println("Coroutine completed with exception $throwable")
        } else {
            println("Coroutine completed without exception")
        }
    }
    delay(50)
    onDestroy()
}

fun onDestroy() {
    println("life-time of scope ends")
    scope.coroutineContext.cancel()
}