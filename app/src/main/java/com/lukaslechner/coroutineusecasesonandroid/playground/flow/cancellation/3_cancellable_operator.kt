package com.lukaslechner.coroutineusecasesonandroid.playground.flow.cancellation

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import kotlin.coroutines.EmptyCoroutineContext

suspend fun main(){
    val scope = CoroutineScope(EmptyCoroutineContext)

    scope.launch {
        flowOf(1,2,3)
            .onCompletion {
                if(it is CancellationException){
                    println("Flow was cancelled")
                }
            }.cancellable()
            .collect{
                println("Collecting $it")

                if(it == 2){
                    cancel()
                }
            }
    }.join()
}
