package com.wix

import kotlinx.coroutines.*
import java.util.concurrent.Executors


fun main(): Unit {
    mainCustomDispatcher()
}

fun mainIO() = runBlocking {

    // Non coroutines world
    launch(Dispatchers.IO) {
        // This is a child scope
        log("Before waiting IO")
        hardWork()
        log("After waiting IO")
    }
}

fun mainDefault() = runBlocking {
    launch(Dispatchers.Default) {
        log("Before waiting Default")
        hardWork()
        log("After waiting  Default")
    }
}

// This will not work on clean kotlin. It needs a ui library like android of javafx
fun mainUi() = runBlocking {
    launch(Dispatchers.Main) {
        log("Before waiting Unconfined")
        hardWork()
        log("After waiting  Unconfined")
    }
}

private val myDispatcher = Executors.newFixedThreadPool(3).asCoroutineDispatcher()

fun mainCustomDispatcher() = runBlocking {
    launch(myDispatcher) {
        log("Before waiting on custom dispatcher")
        hardWork()
        log("After waiting on custom dispatcher")
    }
    launch(myDispatcher) {
        log("Before waiting on custom dispatcher")
        hardWork()
        log("After waiting on custom dispatcher")
    }
    launch(myDispatcher) {
        log("Before waiting on custom dispatcher")
        hardWork()
        log("After waiting on custom dispatcher")
    }
}

// Don't use this dispatcher
fun mainUnconfined() = runBlocking {
    launch(Dispatchers.Unconfined) {
        log("Before waiting Unconfined")
        hardWork()
        log("After waiting  Unconfined")
    }
}


private suspend fun hardWork() {
    delay(1000)
}


