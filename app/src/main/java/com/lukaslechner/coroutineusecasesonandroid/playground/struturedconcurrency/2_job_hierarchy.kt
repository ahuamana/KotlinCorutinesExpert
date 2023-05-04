package com.lukaslechner.coroutineusecasesonandroid.playground.struturedconcurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    val scopeJob = Job()
    val scope = CoroutineScope(Dispatchers.Default + scopeJob)

    var childCoroutineJob:Job?=null
    val corutineJob = scope.launch {
        childCoroutineJob = launch {
            delay(1000)
            println("Child coroutine started")
        }
        println("Coroutine started")
        delay(1000)
    }

    Thread.sleep(100)

    println("Is childCoroutineJob a child of coroutine of coroutineJob = ${corutineJob.children.contains(childCoroutineJob)} ")
    println("Is childCoroutineJob a child of scopeJob = ${scopeJob.children.contains(corutineJob)} ")
}