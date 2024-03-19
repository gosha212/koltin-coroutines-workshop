package com.wix

import kotlinx.coroutines.*

fun main(): Unit {
    cancelOneJob()
}

private fun cancelOneJob() {
    runBlocking {
        val job = launch {
            repeat(1000) { i ->
                log("job: I'm sleeping $i ...")
                delay(500L)
            }
        }
        delay(2300L)
        log("I'm tired of waiting!")
        job.cancel()
        job.join()
        log("Now I can quit.")
    }
}

private fun cancelScope() {
    runBlocking {

        val scope = CoroutineScope(Dispatchers.Default)
        repeat(50) {
            scope.launch {
                log("I'm sleeping $it ...")
                delay(500L * it)
                log("$it")
            }
        }

        log("I'm tired of waiting!")
        delay(2300)
        scope.cancel()
        log("Now I can quit.")
    }
}

private fun timeout() {
    runBlocking {
        withTimeout(1300L) {
            repeat(1000) { i ->
                log("I'm sleeping $i ...")
                delay(500L)
            }
        }
    }
}