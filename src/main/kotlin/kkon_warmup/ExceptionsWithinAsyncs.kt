package kkon_warmup

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {
    try {
        runBlocking {
            try {
                async {
                    try {
                        async {
                            throw MyException("in async")
                        }.await()
                    } catch (e: MyException) {
                        log("inner catch $e")
                    }
                }.await()
            } catch (e: MyException) {
                log("middle catch $e")
            }
        }
    } catch (e: MyException) {
        log("outer catch $e")
    }
}

