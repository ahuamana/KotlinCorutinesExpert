package com.lukaslechner.coroutineusecasesonandroid.playground.exceptionhandling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    try {
        doSomethingSuspend()
    }catch (e: Exception) {
        println("Caught $e")
    }
}

    private suspend fun doSomethingSuspend() {
        coroutineScope {
            launch {
                throw RuntimeException("Crashed coroutine")
            }
        }
    }