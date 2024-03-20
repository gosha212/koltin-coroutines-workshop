package com.wix

import kotlinx.coroutines.*

fun main() {
    mainWithContext()
}

fun mainWithContext() {
    runBlocking {
        log("this is on main thread")
        delay(1000)
        withContext(Dispatchers.Default) {
            log("this is on Default thread")
            delay(2000)
        }
        log("this is on main thread again")
    }
}

fun mainCoroutinesScope() {
    runBlocking {
        hardWork()
    }
}

private suspend fun hardWork() {
    log("Before scope")
    coroutineScope {
        launch(Dispatchers.Default) {
            log("Before waiting coroutine 1")
            delay(2000)
            log("After waiting coroutine 1")
        }

        withContext(Dispatchers.Default) {
            log("Before waiting withContext")
            delay(1000)
            log("After waiting withContext")
        }

        launch {
            log("Before waiting coroutine 2")
            delay(1000)
            log("After waiting coroutine 2")
        }
    }
    log("After scope")
}