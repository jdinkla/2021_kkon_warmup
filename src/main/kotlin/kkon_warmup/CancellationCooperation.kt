package kkon_warmup

import kotlinx.coroutines.*

suspend fun even() = coroutineScope {
    var i = 2
    while(true) {
        delay(200L) // oder yield()
        println("$i")
        i += 2
     }
}

fun main() = runBlocking {
    val job = launch {
        even()
    }
    delay(1000L)
    job.cancelAndJoin()
}

