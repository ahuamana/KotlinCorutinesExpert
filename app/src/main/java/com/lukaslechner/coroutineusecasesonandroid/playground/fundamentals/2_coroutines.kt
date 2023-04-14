package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    println("Main starts: ${Thread.currentThread().name}")
    joinAll(
        async {
            coroutine(1, 500)
        },
        async {
            coroutine(2, 300)
        }
    )
    println("Main ends: ${Thread.currentThread().name}")
}

suspend fun coroutine(number:Int, delay:Long){
    println("Coroutine starts: $number starts work")
    delay(delay)
    println("Coroutine ends: $number ends work")
}