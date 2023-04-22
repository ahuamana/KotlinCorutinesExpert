package com.lukaslechner.coroutineusecasesonandroid.playground.flow.terminal_operators

import kotlinx.coroutines.flow.flow

fun main() {
    val flow = flow {
        kotlinx.coroutines.delay(100)
        println("Emitting 1")
        emit(1)

        kotlinx.coroutines.delay(100)
        println("Emitting 2")
        emit(2)
    }

    val list = buildList {
        add(1)
        println("Adding 1")
        add(2)
        println("Adding 2")
    }
}