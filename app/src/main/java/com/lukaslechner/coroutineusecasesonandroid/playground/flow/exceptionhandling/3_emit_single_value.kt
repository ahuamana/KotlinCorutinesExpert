package com.lukaslechner.coroutineusecasesonandroid.playground.flow.exceptionhandling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

suspend fun main():Unit = coroutineScope {
    launch {
        val stocksFlow = stocksFlows()
            stocksFlow
                .onCompletion { cause ->
                    if(cause != null) {
                        println("Flow completed exceptionally with $cause")
                    } else {
                        println("Flow completed")
                    }
                }.catch {
                    println("Caught $it")
                    emit("Default Value")
                }
                .collect {
                    println("Collecting $it")
                }
    }
}

private fun stocksFlows() : Flow<String> = flow {
    emit("APPL")
    emit("GOOG")
    throw RuntimeException("Something went wrong")
    emit("TESLA")
}