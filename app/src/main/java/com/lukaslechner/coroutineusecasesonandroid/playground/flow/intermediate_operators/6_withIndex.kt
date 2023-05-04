package com.lukaslechner.coroutineusecasesonandroid.playground.flow.intermediate_operators

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.withIndex


suspend fun main() {
    flowOf(1,2,3,4,5,6)
        .withIndex()
        .collect(){
            println("takeWhile: $it")
        }


}