package kkon_warmup

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

interface AndroidMock {
    fun cleanup()
}

class Presenter : AndroidMock, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    fun fetchSomething() {
        launch {
            val stuff = downloadFromSomewhere()
            withContext(Dispatchers.Main) {
                updateUI(stuff)
            }
        }
    }

    override fun cleanup() {
        job.cancel()
    }
}

fun downloadFromSomewhere(): Any {
    return ""
}

fun updateUI(a: Any) {
}
