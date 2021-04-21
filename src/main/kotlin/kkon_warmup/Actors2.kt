package kkon_warmup.actors2

import kkon_warmup.actors2.Msg.Inc
import kkon_warmup.actors2.Msg.Get
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor
import utilities.log

sealed class Msg {
    object Inc : Msg()
    class Get(val response: CompletableDeferred<Int>) : Msg()
}

@ObsoleteCoroutinesApi
fun CoroutineScope.counter(): SendChannel<Msg> = actor<Msg> {
    var counter = 0
    for (msg in channel) {
        log("msg")
        when (msg) {
            is Inc -> counter++
            is Get -> msg.response.complete(counter)
        }
    }
}

suspend fun getCounterValue(actor: SendChannel<Msg>): Int {
    val num = CompletableDeferred<Int>()
    actor.send(Get(num))
    return num.await()
}

@ObsoleteCoroutinesApi
fun main() = runBlocking<Unit> {
    withContext(Dispatchers.Default) {
        val counter = counter()
        log("counter at start: ${getCounterValue(counter)}")
        (1..1_000_000).map {
            launch {
                log("inc")
                counter.send(Inc)
            }
        }.map {
            it.join()
        }
        log("counter at end: ${getCounterValue(counter)}")
        counter.close()
    }
}

