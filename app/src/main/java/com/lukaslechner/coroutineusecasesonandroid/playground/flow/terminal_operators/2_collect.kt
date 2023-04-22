package com.lukaslechner.coroutineusecasesonandroid.playground.flow.terminal_operators

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() {
    val flow = flow {
        kotlinx.coroutines.delay(100)
        println("Emitting 1")
        emit(1)

        kotlinx.coroutines.delay(100)
        println("Emitting 2")
        emit(2)
    }
    runBlocking {
        val item = flow.first { it == 2 }
        println("First item: $item")
    }
}