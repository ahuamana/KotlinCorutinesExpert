package com.lukaslechner.coroutineusecasesonandroid.playground.flow.exceptionhandling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.lang.Exception

suspend fun main():Unit = coroutineScope {
    val stocksFlow = stocksFlows()
    stocksFlow
        .onCompletion { cause ->
            if(cause != null) {
                println("Flow completed exceptionally with $cause")
            } else {
                println("Flow completed")
            }
        }.onEach {
            throw Exception("Something went wrong")
            println("Emitting $it")
        }.catch {
            println("Caught $it")
        }.launchIn(this)
}

private fun stocksFlows() : Flow<String> = flow {
    emit("APPL")
    emit("GOOG")
    throw RuntimeException("Something went wrong")
    emit("TESLA")
}

