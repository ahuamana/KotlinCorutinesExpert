package com.lukaslechner.coroutineusecasesonandroid.playground.exceptionhandling

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Caught $throwable in CoroutineExceptionHandler")
    }

    val scope = CoroutineScope(Job())
    scope.launch(exceptionHandler) {
        //throw RuntimeException("Crashed coroutine")
        launch() {
            println("Starting coroutine 1")
            delay(100)
            throw RuntimeException("Crashed coroutine")
        }

        launch() {
            println("Starting coroutine 2")
            delay(3000)
            throw RuntimeException("Crashed coroutine")
        }
    }

    Thread.sleep(100)
}