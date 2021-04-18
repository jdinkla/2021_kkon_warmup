package kkon_warmup

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun log(str: String) = println("[${Thread.currentThread().name}] $str")

fun main() = runBlocking {
    log("Start")
    launch {
        delay(1000L)
        log("World!")
    }
    log("Hello")
}

