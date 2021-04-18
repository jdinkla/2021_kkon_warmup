package kkon_warmup

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

val firstFlow: Flow<String> = flow {
    println("Step 1")
    emit("Hello")
    println("Step 2")
    emit("Flow")
    println("Step 3")
}

val secondFlow = flowOf("Hello", "Flow")

fun main() = runBlocking<Unit> {
    firstFlow.collect { println(it) }
    secondFlow.collect { println(it) }
}
