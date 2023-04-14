package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    println("Main starts: ${Thread.currentThread().name}")
    joinAll(
        async {
            coroutineWithThreadInfo(1, 500)
        },
        async {
            coroutineWithThreadInfo(2, 300)
        }
    )
    println("Main ends: ${Thread.currentThread().name}")
}

suspend fun coroutineWithThreadInfo(number:Int, delay:Long){
    println("Coroutine starts: $number starts work on ${Thread.currentThread().name}")
    delay(delay)
    println("Coroutine ends: $number ends work on ${Thread.currentThread().name}")
}