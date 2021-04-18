package kkon_warmup

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MyException(val s: String) : Exception() {
    override fun toString(): String = s
}

fun main() {
    try {
        runBlocking {
            try {
                launch {
                    try {
                        launch {
                            throw MyException("in launch")
                        }.join()
                    } catch (e: MyException) {
                        log("inner catch $e")
                    }
                }.join()
            } catch (e: MyException) {
                log("middle catch $e")
            }
        }
    } catch (e: MyException) {
        log("outer catch $e")
    }
}

