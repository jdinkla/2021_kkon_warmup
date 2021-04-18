package kkon_warmup

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

val handler = CoroutineExceptionHandler() { ctx: CoroutineContext, ex: Throwable ->
    log("Gotcha $ex in $ctx")
}

fun main() {
    runBlocking {
        GlobalScope.launch(handler) {
            launch {
                launch {
                    throw MyException("in launch")
                }.join()
            }.join()
        }.join()
    }
}

