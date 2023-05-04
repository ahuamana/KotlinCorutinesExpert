package com.lukaslechner.coroutineusecasesonandroid.playground.struturedconcurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val scope = CoroutineScope(Dispatchers.Default)

    val parentCoroutineScope =  scope.launch {
        launch {
            delay(1000)
            println("Coroutine 1 completed")
        }.invokeOnCompletion {
            if(it != null) {
                println("Coroutine 1 failed")
            }
        }

        launch {
            delay(1000)
            println("Coroutine 2 completed")
        }.invokeOnCompletion {
            if(it != null) {
                println("Coroutine 2 failed")
            }
        }
    }

    //parentCoroutineScope.cancel()
    scope.coroutineContext[Job]!!.cancelAndJoin()
    println("Parent coroutine completed")
}