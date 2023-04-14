package com.lukaslechner.coroutineusecasesonandroid.playground.coroutinebuilders

import kotlinx.coroutines.*

fun main () = runBlocking<Unit> {
        val job = launch(start = CoroutineStart.LAZY) {
          val result = networkRequest()
            println("result received: $result")
        }
        job.join()
        println("printed from outside a coroutine")
}

suspend fun networkRequest():String {
    delay(1000)
    return "Network request result"
}