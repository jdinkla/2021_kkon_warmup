package kkon_warmup

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        try {
            delay(1000L)
        } catch (e: CancellationException) {
            println("job was cancelled")
        }
    }
    yield()
    job.cancelAndJoin()
}

