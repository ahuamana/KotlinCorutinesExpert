package com.lukaslechner.coroutineusecasesonandroid.playground.flow.exceptionhandling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

suspend fun main():Unit = coroutineScope {
    launch {
        val stocksFlow = stocksFlow()
            .map{
                throw RuntimeException("Something went wrong")
            }
        try {
            stocksFlow
                .onCompletion { cause ->
                    if(cause != null) {
                        println("Flow completed exceptionally with $cause")
                    } else {
                        println("Flow completed")
                    }
                }
                .collect {
                    println("Collecting $it")
                }
        }catch (e: Exception) {
            println("Caught $e")
        }

    }
}

private fun stocksFlow() : Flow<String> = flow {
    emit("APPL")
    throw RuntimeException("Something went wrong")
    emit("GOOG")
    emit("TESLA")
}