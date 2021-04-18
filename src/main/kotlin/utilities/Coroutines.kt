package utilities

import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

fun CoroutineContext.elements(): List<CoroutineContext.Element> = this.fold(listOf()) {
    ls, elem ->
    listOf(elem) + ls
}

fun CoroutineScope.printContext(msg: String = "") {
    val s = this.coroutineContext.elements().joinToString(", ")
    println("$msg: thread=${Thread.currentThread().name}, context=[${s}]")
}

