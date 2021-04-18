package kkon_warmup

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock

class AtomicCounter() {
    val c = AtomicInteger(0)
    fun inc() {
        c.incrementAndGet()
    }
}

fun main() = runBlocking {
    val c = AtomicCounter()
    (1..1_000_000).map {
        launch(Dispatchers.Default) {
            c.inc()
        }
    }.map {
        it.join()
    }
    println("Counter: ${c.c}")
}
