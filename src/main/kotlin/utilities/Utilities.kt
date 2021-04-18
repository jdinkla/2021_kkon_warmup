package utilities

import kotlin.system.measureTimeMillis

fun log(str: String) = println("[${Thread.currentThread().name}] $str")

fun measure(text: String = "", block: () -> Unit): Long {
    val ms = measureTimeMillis {
        block()
    }
    println("'$text' took $ms ms")
    return ms
}
