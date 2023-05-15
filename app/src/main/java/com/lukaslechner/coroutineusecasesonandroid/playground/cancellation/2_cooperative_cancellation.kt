package com.lukaslechner.coroutineusecasesonandroid.playground.cancellation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

fun main() = runBlocking{
    val job = launch(Dispatchers.Default) {
        repeat(10) { i ->
            yield()//ensureActive()
            println("job: I'm sleeping $i ...")
            Thread.sleep(100)
        }
    }

    delay(250)
    println("Canceling job")
    job.cancel()
}