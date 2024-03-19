package com.wix

import kotlinx.coroutines.*

fun main() {
    tryCatch()
}


private fun willNotWork() = runBlocking {

    try {
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
    } catch (e: RuntimeException) {
        log("Caught $e")
    }

}

private fun tryCatch() {
    runBlocking {
        try {
            coroutineScope {
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
        } catch (e: RuntimeException) {
            log("Caught $e")
        }
    }

}

private fun exceptionHandler() {
    runBlocking {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("CoroutineExceptionHandler got $exception")
        }

        val scope = CoroutineScope(Dispatchers.Default + handler)

        val job = scope.launch {
            log("About to sleep")
            delay(1000L)
            throw RuntimeException("I failed")
        }

        job.join()
    }

    log("Done")
}

