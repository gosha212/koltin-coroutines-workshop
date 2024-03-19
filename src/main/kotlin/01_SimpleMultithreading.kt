package com.wix

import kotlin.concurrent.thread

fun main() {
    setProgressBarVisibility(true)

    executeNetworkRequest {
        insertToDatabase(it) {
            executeOnMainThread {
                setProgressBarVisibility(false)
            }
        }
    }
}

private fun executeOnMainThread(action: () -> Unit) {
    log("Executing on main thread")
    // In Android we have the Handler class to post actions to the main thread
    action()
}

private fun setProgressBarVisibility(visible: Boolean) {
    log("Setting progress bar visibility to $visible")
}

private fun executeNetworkRequest(callback: (String) -> Unit) {
    thread(name = "NetworkThread") {
        log("Executing network request")
        Thread.sleep(3000)
        callback("Response from network")
    }
}

private fun insertToDatabase(data: String, callback: (String) -> Unit) {
    thread(name = "DatabaseThread") {
        log("Executing database request")
        Thread.sleep(2000)
        callback("Inserted to database: $data")
    }
}
