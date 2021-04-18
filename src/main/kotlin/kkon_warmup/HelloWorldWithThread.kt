package kkon_warmup

import kotlin.concurrent.thread
import java.lang.Thread.sleep

fun main() {
    val t = thread {
        sleep(1000L)
        println("World!")
    }
    println("Hello")
    t.join()
}

