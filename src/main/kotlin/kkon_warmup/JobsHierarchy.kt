package kkon_warmup

import kotlinx.coroutines.*

fun CoroutineScope.show(id: String)
    = println("$id: ${coroutineContext.job} ${coroutineContext.job.children.toList()}")

fun main() = runBlocking<Unit> {
    show("runBlocking")
    launch {
        show("launch")
        delay(200L)
    }
    async {
        show("async")
        delay(200L)
    }
    delay(100L)
    show("runBlocking")
}

