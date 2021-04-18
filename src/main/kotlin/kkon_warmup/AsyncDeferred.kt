package kkon_warmup

import kotlinx.coroutines.*

fun main() = runBlocking {
    val deferred: Deferred<Int> = async {
        delay(1000L)
        42
    }
    val result = deferred.await()
    println("active: ${deferred.isActive}")
    println("canceled: ${deferred.isCancelled}")
    println("completed: ${deferred.isCompleted}")
}

