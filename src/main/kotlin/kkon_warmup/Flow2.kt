package kkon_warmup

import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import utilities.log
import java.util.concurrent.Executors

val aFlow = (1..3).asFlow()
    .onEach { log("onEach $it") }
    .map { 2 * it }
    .map { it + 1 }
    .filter { it % 2 == 1 }

val dispatcher1 = Executors.newFixedThreadPool(1).asCoroutineDispatcher()
val dispatcher2 = Executors.newFixedThreadPool(1).asCoroutineDispatcher()

fun main() = runBlocking<Unit> {
    launch(dispatcher1) {
        aFlow.flowOn(dispatcher2).collect { log("$it") }
    }.join()
}

// this program does not terminate because the pools have to be shut down
