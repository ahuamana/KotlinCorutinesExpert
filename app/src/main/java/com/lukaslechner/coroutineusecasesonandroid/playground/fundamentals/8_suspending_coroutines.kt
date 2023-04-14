package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    println("Main starts: ${Thread.currentThread().name}")
    joinAll(
        async {
            suspendingCoroutine(1, 500)
        },
        async {
            suspendingCoroutine(2, 300)
        },
        async {
            repeat(5) {
                println("other task is workin on ${Thread.currentThread().name}")
                delay(100)
            }
            suspendingCoroutine(3, 100)
        }

    )
    println("Main ends: ${Thread.currentThread().name}")
}

suspend fun suspendingCoroutine(number:Int, delay:Long){
    println("Coroutine starts: $number starts work on ${Thread.currentThread().name}")
    delay(delay)
    println("Coroutine ends: $number ends work on ${Thread.currentThread().name}")
}