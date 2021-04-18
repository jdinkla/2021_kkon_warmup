package kkon_warmup

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.locks.ReentrantLock

val f = listOf("a").map { it -> it }

class ReentrantLockCounter() {
    var c: Int = 0
        private set
    private val lock = ReentrantLock()
    fun inc() {
        lock.lock()
        c += 1
        lock.unlock()
    }
}

fun main() = runBlocking {
    val c = ReentrantLockCounter()
    (1..1_000_000).map {
        launch(Dispatchers.Default) {
            c.inc()
        }
    }.map {
        it.join()
    }
    println("Counter: ${c.c}")
}
