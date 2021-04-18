package kkon_warmup

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

fun CoroutineScope.extend() {
}

fun CoroutineContext.toString(): String {
    val ls = mutableListOf<CoroutineContext.Element>()
    fold(ls) { ls, elem ->
        ls.add(elem)
        ls
    }
    return ls.toString()
}


fun main() {
    runBlocking {
        println(coroutineContext.toString())
        launch {
            println(coroutineContext)
        }
    }
}