package com.wix

import kotlinx.coroutines.*

fun main() {
    // Non coroutines world
    runBlocking {
        // This is a scope
        launch {
            // This is a child scope
            log("Before waiting")
            hardWork()
            log("After waiting")
        }
        log("Hello world!")
    }
}

private suspend fun hardWork() {
    delay(1000)
}

