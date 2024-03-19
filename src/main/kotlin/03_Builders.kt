package com.wix

import kotlinx.coroutines.*

fun main() {
    mainLaunch()
}

fun mainLaunch(): Unit = runBlocking {
    launch {
        log("Before waiting coroutine 1")
        delay(2000)
        log("After waiting coroutine 1")
    }

    launch {
        log("Before waiting coroutine 2")
        delay(1000)
        log("After waiting coroutine 2")
    }
}

fun mainAsync(): Unit = runBlocking {
    val deferred1 = async {
        log("Before waiting async 1")
        delay(3000)
        log("After waiting async 1")
        1
    }

    val deferred2 = async {
        log("Before waiting async 2")
        delay(1000)
        log("After waiting async 2")
        2
    }

    val result1 = deferred1.await()
    val result2 = deferred2.await()
    log("Result: ${result1 + result2}")
}

fun mainMultipleAsync(): Unit = runBlocking {
    val deferred1 = async {
        log("Before waiting async 1")
        delay(3000)
        log("After waiting async 1")
        1
    }

    val deferred2 = async {
        log("Before waiting async 2")
        delay(1000)
        log("After waiting async 2")
        2
    }

    val deferred3 = async {
        log("Before waiting async 3")
        delay(2000)
        log("After waiting async 3")
        3
    }

    val allDeferred = listOf(deferred1, deferred2, deferred3)
    val results = allDeferred.awaitAll()
    log("Result: ${results.sum()}")
}