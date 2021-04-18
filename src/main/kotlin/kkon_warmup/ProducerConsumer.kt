package kkon_warmup

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun produce(channel: SendChannel<Int>) {
    (1..1000).map {
        log("send $it")
        channel.send(it)
    }
}

suspend fun consume(id: Int, channel: ReceiveChannel<Int>) {
    repeat(2) {
        log("client $id before receive")
        val value = channel.receive()
        log("client $id received $value")
    }
}

fun main() {
    val channel = Channel<Int>(Channel.RENDEZVOUS)
    runBlocking(Dispatchers.Default) {
        launch {
            produce(channel)
        }
        (1..2).map {
            launch {
                consume(it, channel)
            }
        }.map { it.join() }
        channel.cancel()
    }
}

