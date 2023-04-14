package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals

import kotlin.concurrent.thread


fun main() {
    println("Main starts: ${Thread.currentThread().name}")
    theadroutine(1, 500)
    theadroutine(2, 300)
    Thread.sleep(1000)
    println("Main ends: ${Thread.currentThread().name}")
}

fun theadroutine(number:Int, delay:Long){
    thread {
        println("Routine starts: $number starts work on ${Thread.currentThread().name}")
        Thread.sleep(delay)
        println("Routine ends: $number ends work on ${Thread.currentThread().name}")
    }
}