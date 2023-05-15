package com.lukaslechner.coroutineusecasesonandroid.playground.exceptionhandling

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun main() {
    val exceptionHAndler = CoroutineExceptionHandler{ coroutineContext, throwable ->
        println("Caught $throwable in CoroutineExceptionHandler")
    }
    val scope = CoroutineScope(Job())
    scope.launch() {
        //throw RuntimeException("Crashed coroutine")
        launch(exceptionHAndler) {
            throw RuntimeException("Crashed coroutine")
        }
    }

    Thread.sleep(100)
}