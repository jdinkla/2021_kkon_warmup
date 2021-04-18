package kkon_warmup

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

open class Counter {
    var c: Int = 0
        private set
    open fun inc() {
        c += 1
    }
}

fun main() = runBlocking(Dispatchers.Default) {
    val c = Counter()
    (1..1_000_000).map {
        launch {
            c.inc()
        }
    }.map {
        it.join()
    }
    println("Counter: ${c.c}")
}

