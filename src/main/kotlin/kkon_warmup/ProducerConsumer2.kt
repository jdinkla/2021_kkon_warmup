package kkon_warmup

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun CoroutineScope.integers(): ReceiveChannel<Int> = produce {
    for (it in 1..1000) {
        println("produce $it")
        send(it)
    }
}

@ExperimentalCoroutinesApi
fun main() {
    runBlocking {
        val channel = integers()
        (1..2).map {
            launch {
                consume(it, channel)
            }
        }.map { it.join() }
        channel.cancel()
    }
}

