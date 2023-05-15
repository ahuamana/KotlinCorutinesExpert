package com.lukaslechner.coroutineusecasesonandroid.playground.flow.cancellation

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import java.math.BigInteger
import kotlin.coroutines.EmptyCoroutineContext

suspend fun main(){
    val scope = CoroutineScope(EmptyCoroutineContext)

    scope.launch {
        intFlow()
            .onCompletion {
                if(it is CancellationException){
                    println("Flow was cancelled")
                }
            }.collect{
                println("Collecting $it")

                if(it == 2){
                    cancel()
                }
            }
    }.join()
}

private fun intFlow() = flow {
    emit(1)
    emit(2)

    // currentCoroutineContext().ensureActive() // yield()
    println("Starting factorial calculation for 1000")
    calculateFactorialOf(1_000)
    println("Done with factorial calculation for 1000")
    emit(3)
}

private suspend fun calculateFactorialOf(number:Int):BigInteger{
    var factorial = BigInteger.ONE
    for(i in 1..number){
        yield()
        factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
    }
    return factorial
}