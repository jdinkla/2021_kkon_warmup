package kkon_warmup.extra

import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import utilities.log
import java.util.concurrent.Executors

val pool1 = Executors.newFixedThreadPool(1)!!
val dispatcher1 = pool1.asCoroutineDispatcher()

val pool2 = Executors.newFixedThreadPool(1)!!
val dispatcher2 = pool2.asCoroutineDispatcher()

val aFlow = (1..3).asFlow()
    .onEach { log("onEach $it") }
    .map { 2 * it }
    .map { it + 1 }
    .filter { it % 2 == 1 }

fun main() = runBlocking<Unit> {
    launch(dispatcher1) {
        aFlow.flowOn(dispatcher2).collect { log("$it") }
    }.join()

    pool1.shutdownNow()
    pool2.shutdownNow()
}
