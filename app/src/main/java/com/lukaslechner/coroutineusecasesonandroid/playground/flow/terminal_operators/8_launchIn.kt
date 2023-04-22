package com.lukaslechner.coroutineusecasesonandroid.playground.flow.terminal_operators

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

fun main() {
    val flow = flow {
        kotlinx.coroutines.delay(100)
        println("Emitting 1")
        emit(1)

        kotlinx.coroutines.delay(100)
        println("Emitting 2")
        emit(2)
    }

    val scope = CoroutineScope(EmptyCoroutineContext)

    //concurrently
    flow
        .onEach { println("Processing $it with launchIn() - 1") }
        .launchIn(scope)

    flow
        .onEach { println("Processing $it with launchIn() - 2") }
        .launchIn(scope)

    /*scope.launch {
    //Secuentially
        flow.collect {
            println("Collected $it in collect - 1")
        }
        flow.collect {
            println("Collected $it in collect - 2")
        }

    }*/

    Thread.sleep(1000)
}