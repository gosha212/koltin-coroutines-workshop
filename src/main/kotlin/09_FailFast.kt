package com.wix

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

fun main() {
    failFast()
}

private fun failFast() {
    runBlocking {
        repeat(10) {
            launch {
                log("About to sleep $it")
                delay(it * 500L)
                if (it == 5) {
                    throw RuntimeException("I failed")
                }
                log("Completed $it")
            }
        }
    }
}

private fun failSlow() {
    runBlocking {
        supervisorScope {
            repeat(10) {
                launch {
                    log("About to sleep $it")
                    delay(it * 500L)
                    if (it == 5) {
                        throw RuntimeException("I failed")
                    }
                    log("Completed $it")
                }
            }
        }
    }
}