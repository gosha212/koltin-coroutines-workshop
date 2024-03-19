package com.wix

import kotlinx.coroutines.*

fun main(): Unit = runBlocking {

    log("Parent context: $coroutineContext")
    launch {
        log("Child context: $coroutineContext")

        launch(Dispatchers.IO + CoroutineName("Child of child")) {
            log("Child of child context: $coroutineContext")

            launch {
                log("Child of child of child context: $coroutineContext")
            }
        }
    }
}
