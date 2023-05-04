package com.lukaslechner.coroutineusecasesonandroid.playground.flow.intermediate_operators

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.takeWhile


suspend fun main() {
    flowOf(1,2,3,4,5,6)
        .takeWhile{
            it < 4
        }
        .collect(){
            println("takeWhile: $it")
        }

    flowOf(1,2,3,4,5,6)
        .take(3)
        .collect(){
            println("take: $it")
        }
}