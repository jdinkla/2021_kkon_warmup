package kkon_warmup

import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import utilities.measure
import java.lang.Thread.sleep
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

fun delay(pool: ExecutorService, n: Int) {
    val dispatcher = pool.asCoroutineDispatcher()
    runBlocking {
        (1..n).map {
            launch(dispatcher) {
                delay(1000L)
            }
        }.map {
            it.join()
        }
    }
}

fun main() {
    val pool = Executors.newFixedThreadPool(4)
    measure("using threads and sleep") {
        sleep(pool, 5)
    }
    measure("using coroutines and delay") {
        delay(pool, 5000)
    }
    pool.shutdownNow()
}