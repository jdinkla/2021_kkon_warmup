package kkon_warmup

import java.lang.Thread.sleep
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis

fun sleep(pool: ExecutorService, n: Int) {
    (1..n).map {
        pool.submit {
            sleep(1000L)
        }
    }.map {
        it.get()
    }
}

fun main() {
    val pool = Executors.newFixedThreadPool(4)
    val ms = measureTimeMillis {
        sleep(pool, 5)
    }
    println("took $ms ms")
    pool.shutdownNow()
}

