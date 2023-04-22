package com.lukaslechner.coroutineusecasesonandroid.playground.flow.basics

import com.lukaslechner.coroutineusecasesonandroid.playground.utils.printWithTimePassed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Dispatcher
import java.math.BigInteger

fun main () = runBlocking {
    val startTime = System.currentTimeMillis()
    launch {
        calculateFactorialOf(5).collect {
            printWithTimePassed(it, startTime = startTime)
        }
    }
    println("Ready in ${System.currentTimeMillis() - startTime} ms")
}

private fun calculateFactorialOf(number:Int): Flow<BigInteger> = flow{
    var factorial = BigInteger.ONE
    for (i in 1..number) {
        Thread.sleep(10)
        factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
        emit(factorial)
    }
}.flowOn(Dispatchers.Default)