package com.lukaslechner.coroutineusecasesonandroid.playground.coroutinebuilders

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    val startTime = System.currentTimeMillis()

    val resultList = mutableListOf<String>()

   val job1 = launch {
       val result1 = networkCall(1)
        resultList.add(result1)
        println("result received: $result1 aferter ${elapsedMillis(startTime)} ms")
    }
    val job2 =launch {
        val result2 = networkCall(2)
        resultList.add(result2)
        println("result received: $result2 aferter ${elapsedMillis(startTime)} ms")
    }

    job1.join()
    job2.join()

    println("resultList: $resultList after ${elapsedMillis(startTime)} ms")


}

suspend fun networkCall(number:Int): String {
    delay(500)
    return "Network request result $number"
}

fun elapsedMillis(startTime : Long) = (System.currentTimeMillis() - startTime)