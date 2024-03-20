package com.wix

import kotlinx.coroutines.*

fun main() {
    // Non coroutines world
    runBlocking {
        // This is a scope
        launch {
            // This is a child scope
            log("Before doing hard work")
            hardWork()
            log("After the hard work")
        }
        delay(500)
        log("Hello world!")
    }
}

private suspend fun hardWork() {
    delay(1000)
}

