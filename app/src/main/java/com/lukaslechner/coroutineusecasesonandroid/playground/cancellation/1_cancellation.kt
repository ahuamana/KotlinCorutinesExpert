package com.lukaslechner.coroutineusecasesonandroid.playground.cancellation

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main () = runBlocking<Unit>{
    val job = launch {
        repeat(10) { i ->
            println("job: I'm sleeping $i ...")
            try {
                delay(100)
            } catch (e: Exception) {
                println("job: Caught $e")
            }
        }
    }

    delay(250)
    println("Canceling job")
    job.cancel()
}