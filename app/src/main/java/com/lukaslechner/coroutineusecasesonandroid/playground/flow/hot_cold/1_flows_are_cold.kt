package com.lukaslechner.coroutineusecasesonandroid.playground.flow.hot_cold

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

fun coldFlow() = flow {
    println("Emitting 1")
    emit(1)


    delay(1000)
    println("Emitting 2")
    emit(2)

    delay(1000)
    println("Emitting 3")
    emit(3)

}

suspend fun main():Unit = coroutineScope {
    var job = launch {
        coldFlow()
            .onCompletion {
                println("Flow completed")
            }
            .collect {
            println("Collected $it")
        }
    }

    delay(1500)

    job.cancel()
}