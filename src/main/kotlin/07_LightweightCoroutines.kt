package com.wix

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    coroutines()
}

fun coroutines() = runBlocking {
    repeat(100_000) {
        launch {
            delay(1000)
            log("$it")
        }
    }
}

fun threads() {
    repeat(100_000) {
        Thread {
            Thread.sleep(1000)
            log("$it")
        }.start()
    }
}