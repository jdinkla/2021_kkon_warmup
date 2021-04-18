package kkon_warmup.context

import kotlinx.coroutines.*
import utilities.printContext
import java.util.concurrent.Executors

val pool = Executors.newFixedThreadPool(4).asCoroutineDispatcher()

val handler = CoroutineExceptionHandler() { context, ex ->
    println("Gotcha $ex")
}

fun main() = runBlocking<Unit> {
    launch(pool) {
        printContext("launch #1")
        launch(handler) {
            printContext("launch #2")
        }
        withContext(Dispatchers.IO + handler) {
            printContext("withContext")
        }
        coroutineScope {
            printContext("coroutineScope")
        }
    }
}

