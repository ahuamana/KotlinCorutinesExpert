package com.lukaslechner.coroutineusecasesonandroid.playground.flow.terminal_operators

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.toSet
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
        val item = flow.fold(5){acc, value ->
            println("Acc: $acc, Value: $value")
            acc + value
        }

        println("First item: $item")
    }

}