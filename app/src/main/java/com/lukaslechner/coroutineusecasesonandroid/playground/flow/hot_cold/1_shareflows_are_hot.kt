package com.lukaslechner.coroutineusecasesonandroid.playground.flow.hot_cold

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

fun main (){

    val sharedFlow = MutableSharedFlow<Int>()
    val scope = CoroutineScope(Dispatchers.Default)
    scope.launch {
        repeat(5){
            println("SharedFlow: Emitting $it")
            sharedFlow.emit(it)
            delay(200)
        }
    }

    //Thread.sleep(500)

    scope.launch {
        sharedFlow.collect(){
            println("SharedFlow: Collected $it in collector 1")
        }
    }

    scope.launch {
        sharedFlow.collect(){
            println("SharedFlow: Collected $it in collector 2")
        }
    }

    Thread.sleep(1500)
}