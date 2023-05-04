package com.lukaslechner.coroutineusecasesonandroid.playground.flow.intermediate_operators

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOf


suspend fun main() {
    flowOf(1,2,3,4,5,6)
        .filterIsInstance<Int>()
        .collect(){
            println(it)
        }
}