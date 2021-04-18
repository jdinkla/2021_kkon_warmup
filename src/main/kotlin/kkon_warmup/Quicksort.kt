package kkon_warmup

import kotlinx.coroutines.*
import utilities.measure
import kotlin.random.Random

const val BUCKET_SIZE = 2048

fun <T> List<T>.pivot(): T = this[Random.nextInt(0, size)]

suspend fun <T : Comparable<T>> coquicksort(ls: List<T>): List<T> = coroutineScope {
    if (ls.size <= BUCKET_SIZE) {
        ls.sorted()
    } else {
        val pivot = ls.pivot()
        val (lower, higher) = ls.partition { it < pivot }
        val sortedLower = async { coquicksort(lower) }
        val sortedHigher = async { coquicksort(higher) }
        sortedLower.await() + sortedHigher.await()
    }
}

fun <T : Comparable<T>> quicksort(ls: List<T>): List<T> {
    if (ls.size <= BUCKET_SIZE) {
        return ls.sorted()
    }
    val pivot = ls.pivot()
    val (lower, higher) = ls.partition { it < pivot }
    return quicksort(lower) + quicksort(higher)
}

fun main() {
    val ls = (1..5_000_000).map { Random.nextInt() }
    measure("sorted") {
        ls.sorted()
    }
    measure("sequential") {
        quicksort(ls)
    }
    measure("coroutines") {
        runBlocking(Dispatchers.Default) {
            coquicksort(ls)
        }
    }
}

