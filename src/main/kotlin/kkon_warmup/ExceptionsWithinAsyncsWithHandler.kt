package kkon_warmup

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


fun main() {
    runBlocking {
        GlobalScope.async(handler) {
            async {
                async {
                    throw MyException("in launch")
                }.join()
            }.join()
        }.join()
    }
}

