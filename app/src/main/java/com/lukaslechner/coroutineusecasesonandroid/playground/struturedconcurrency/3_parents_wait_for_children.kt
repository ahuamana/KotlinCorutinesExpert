package com.lukaslechner.coroutineusecasesonandroid.playground.struturedconcurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val scope = CoroutineScope(Dispatchers.Default)

   val parentCoroutineScope =  scope.launch {
        launch {
            delay(1000)
            println("Coroutine 1 completed")
        }

        launch {
            delay(1000)
            println("Coroutine 2 completed")
        }
    }

    parentCoroutineScope.join()
    println("Parent coroutine completed")
}