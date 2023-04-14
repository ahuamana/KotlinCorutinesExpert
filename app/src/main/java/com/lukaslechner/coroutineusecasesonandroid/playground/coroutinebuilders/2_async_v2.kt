package com.lukaslechner.coroutineusecasesonandroid.playground.coroutinebuilders

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis()

   val job1 = async(start = CoroutineStart.LAZY) {
       val result1 = networkCall2(1)
       println("result received: $result1 aferter ${elapsedMillis2(startTime)} ms")
       result1
    }
    val job2 =async() {
        val result2 = networkCall2(2)
        println("result received: $result2 aferter ${elapsedMillis2(startTime)} ms")
        result2
    }
    job1.start()

    println("resultList: ${job1.await()} ${job2.await()} after ${elapsedMillis2(startTime)} ms")

}

suspend fun networkCall2(number:Int): String {
    delay(500)
    return "Network request result $number"
}

fun elapsedMillis2(startTime : Long) = (System.currentTimeMillis() - startTime)