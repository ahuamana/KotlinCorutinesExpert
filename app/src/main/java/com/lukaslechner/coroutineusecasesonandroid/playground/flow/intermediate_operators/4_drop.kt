package com.lukaslechner.coroutineusecasesonandroid.playground.flow.intermediate_operators

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.flow.transform


suspend fun main() {
    flowOf(1,2,3,4,5,6)
        .drop(3)
        .collect(){
            println("takeWhile: $it")
        }

    flowOf(1,2,3,4,5,6)
        .dropWhile{
            it < 4
        }
        .collect(){
            println("take: $it")
        }

}