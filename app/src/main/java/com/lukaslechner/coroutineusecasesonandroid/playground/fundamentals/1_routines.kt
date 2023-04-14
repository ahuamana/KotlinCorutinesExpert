package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals


fun main() {
    println("Main starts: ${Thread.currentThread().name}")
    routine(1, 500)
    routine(2, 300)
    println("Main ends: ${Thread.currentThread().name}")
}

fun routine(number:Int, delay:Long){
    println("Routine starts: $number starts work")
    Thread.sleep(delay)
    println("Routine ends: $number ends work")
}