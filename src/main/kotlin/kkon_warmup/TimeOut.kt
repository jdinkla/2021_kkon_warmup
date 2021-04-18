package kkon_warmup

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = withTimeout(500L) {
        launch {
            try {
                delay(1000L)
            } catch (e: TimeoutCancellationException) {
                println("job has timeout")
            }
        }
    }
    yield()
    job.cancelAndJoin()
}

