package kkon_warmup

import kotlinx.coroutines.runBlocking

val iteratorExample: Iterator<Int> = iterator {
    repeat(3) {
        println("iterator $it")
        yield(it)
    }
}

val sequenceExample: Sequence<Int> = sequence {
    repeat(5) {
        println("sequence $it")
        yield(it)
    }
}

fun main() = runBlocking {
    iteratorExample.forEach {
        println("Got $it from iterator")
    }
    sequenceExample.take(2).forEach {
        println("Got $it from sequence")
    }
}

