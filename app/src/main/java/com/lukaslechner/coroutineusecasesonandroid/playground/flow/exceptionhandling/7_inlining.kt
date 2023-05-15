package com.lukaslechner.coroutineusecasesonandroid.playground.flow.exceptionhandling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.lang.Exception

suspend fun main():Unit = coroutineScope {
    flow{
        emit(1)
        emit(2)
        emit(3)
    }.collect{
        println("Collected $it")
    }
}


val inlinedFlow = flow<Int>{
    println("Collented 1")
    println("Collented 2")
    println("Collented 3")
}

