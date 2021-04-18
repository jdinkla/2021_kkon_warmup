package kkon_warmup.log

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import utilities.log

suspend fun produce(channel: SendChannel<Int>) {
    (1..1000).map {
        log("produce $it")
        channel.send(it)
    }
}

suspend fun consume(id: Int, channel: ReceiveChannel<Int>) {
    repeat(2) {
        log("client $id starts consuming")
        val value = channel.receive()
        log("client $id consumed $value")
    }
}

fun main() {
    val dispatcher = Dispatchers.Unconfined
    val channel = Channel<Int>(Channel.RENDEZVOUS)
    runBlocking {
        launch(dispatcher) {
            produce(channel)
        }
        (1..2).map {
            launch(dispatcher) {
                consume(it, channel)
            }
        }.map { it.join() }
        channel.cancel()
    }
}

