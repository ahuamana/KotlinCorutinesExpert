package com.lukaslechner.coroutineusecasesonandroid.playground.struturedconcurrency

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

fun main(){


    val exceptionHAndler = CoroutineExceptionHandler(){ _, throwable ->
        println("Caught exception $throwable")
    }

    //superVisorJob is a job that is not affected by the failure of its children
    //job is affected by the failure of its children

    val scope = CoroutineScope(SupervisorJob() + exceptionHAndler)

    scope.launch {
        println("Coroutine 1 started")
        delay(1000)
        throw RuntimeException()
    }
    scope.launch {
        println("Coroutine 2 started")
        delay(1000)
        println("Coroutine 2 completed")
    }.invokeOnCompletion {
        throwable -> if(throwable != null) {
            println("Coroutine 2 failed")
        }
    }
    //If scope is not active, it means that u can't launch any new coroutines in it
    println("Scope is active: ${scope.isActive}")
}