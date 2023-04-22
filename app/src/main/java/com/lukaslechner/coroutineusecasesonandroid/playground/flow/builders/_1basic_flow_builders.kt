package com.lukaslechner.coroutineusecasesonandroid.playground.flow.builders

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

suspend fun main () {
    val firstFlow = flowOf<Int>(1).collect{ emmittedValue ->
        println("firsFlow $emmittedValue")
    }
    val secondFlow = flowOf<Int>(1,2,3)
    secondFlow.collect { emmittedValue ->
        println("secondFlow $emmittedValue")
    }

    listOf("a","b","c").asFlow().collect(){ emmittedValue ->
        println("listOf $emmittedValue")
    }
    flow{
        kotlinx.coroutines.delay(2000)
        emit("item emitter afet 2 seconds")

        emitAll(secondFlow)
    }.collect(){ emmittedValue ->
        println("flow{} $emmittedValue")
    }

}