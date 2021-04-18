package kkon_warmup

import kotlinx.coroutines.*

fun Job.status(id: String)
    = println("$id: active: ${isActive}, completed: ${isCompleted}, canceled: ${isCancelled}")

fun main() = runBlocking {
    val job = launch {
        coroutineContext[Job]?.status("1")
        delay(1000L)
        coroutineContext[Job]?.status("2")
    }
    yield()
    job.status("3")
    job.cancel()
    job.status("4")
    job.join()
    job.status("5")
}
