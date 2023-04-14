package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals

import kotlinx.coroutines.*


fun main() = runBlocking {
    println("Main starts: ${Thread.currentThread().name}")
    joinAll(
        async {
            threadSwitchingCoroutine(1, 500)
        },
        async {
            threadSwitchingCoroutine(2, 300)
        }
    )
    println("Main ends: ${Thread.currentThread().name}")
}

suspend fun threadSwitchingCoroutine(number:Int, delay:Long){
    println("Coroutine starts: $number starts work on ${Thread.currentThread().name}")
    delay(delay)
    withContext(Dispatchers.Default){
        println("Coroutine $number has finished work on ${Thread.currentThread().name}")
    }
}