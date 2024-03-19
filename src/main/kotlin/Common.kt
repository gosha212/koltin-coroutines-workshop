package com.wix

import java.time.LocalDateTime

fun log(message: String) {
    println("[${Thread.currentThread().name}] [${LocalDateTime.now()}] $message")
}